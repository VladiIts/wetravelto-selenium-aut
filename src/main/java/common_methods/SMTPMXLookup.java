package common_methods;

//Code Source: http://www.rgagnon.com/javadetails/java-0452.html

/*
 * A list a possible SMTP Reply Codes:

Code Description
211  System status, or system help reply.
214  Help message.
220  Domain service ready.
     Ready to start TLS.
221  Domain service closing transmission channel.
250  OK, queuing for node node started.
     Requested mail action okay, completed.
251  OK, no messages waiting for node node.
     User not local, will forward to forwardpath.
252  OK, pending messages for node node started.
     Cannot VRFY user (e.g., info is not local), 
     but will take message for this user and attempt delivery.
253  OK, messages pending messages for node node started.
354  Start mail input; end with ..
355  Octet-offset is the transaction offset.
421  Domain service not available, closing transmission channel.
432  A password transition is needed.
450  Requested mail action not taken: mailbox unavailable.
     (ex. mailbox busy)
451  Requested action aborted: local error in processing.
     Unable to process ATRN request now
452  Requested action not taken: insufficient system storage.
453  You have no mail.
454  TLS not available due to temporary reason.
     Encryption required for requested authentication mechanism.
458  Unable to queue messages for node node.
459  Node node not allowed: reason.
500  Command not recognized: command.
     Syntax error.
501  Syntax error, no parameters allowed.
502  Command not implemented.
503  Bad sequence of commands.
504  Command parameter not implemented.
521  Machine does not accept mail.
530  Must issue a STARTTLS command first.
     Encryption required for requested authentication mechanism.
534  Authentication mechanism is too weak.
538  Encryption required for requested authentication mechanism.
550  Requested action not taken: mailbox unavailable.
551  User not local; please try forwardpath.
552  Requested mail action aborted: exceeded storage allocation.
553  Requested action not taken: mailbox name not allowed.
554  Transaction failed.

 * 
 */

import java.io.*;
import java.net.*;
import java.util.*;

import javax.naming.*;
import javax.naming.directory.*;



public class SMTPMXLookup {
	
	public static ArrayList mxList;
	
   private static int hear( BufferedReader in ) throws IOException {
     String line = null;
     int res = 0;

     while ( (line = in.readLine()) != null ) {
         String pfx = line.substring( 0, 3 );
         try {
            res = Integer.parseInt( pfx );
         }
         catch (Exception ex) {
            res = -1;
         }
         if ( line.charAt( 3 ) != '-' ) break;
     }

     return res;
     }

   private static void say( BufferedWriter wr, String text )
      throws IOException {
     wr.write( text + "\r\n" );
     wr.flush();

     return;
     }
     @SuppressWarnings({ "unchecked", "rawtypes" })
	private static ArrayList getMX( String hostName )
         throws NamingException {
     // Perform a DNS lookup for MX records in the domain
     Hashtable env = new Hashtable();
     env.put("java.naming.factory.initial",
             "com.sun.jndi.dns.DnsContextFactory");
     DirContext ictx = new InitialDirContext( env );
     Attributes attrs = ictx.getAttributes
                           ( hostName, new String[] { "MX" });
     Attribute attr = attrs.get( "MX" );

     // if we don't have an MX record, try the machine itself
     if (( attr == null ) || ( attr.size() == 0 )) {
       attrs = ictx.getAttributes( hostName, new String[] { "A" });
       attr = attrs.get( "A" );
       if( attr == null )
            throw new NamingException
                     ( "No match for name '" + hostName + "'" );
     }
         // Huzzah! we have machines to try. Return them as an array list
     // NOTE: We SHOULD take the preference into account to be absolutely
     //   correct. This is left as an exercise for anyone who cares.
     ArrayList res = new ArrayList();
     NamingEnumeration en = attr.getAll();

     while ( en.hasMore() ) {
        String mailhost;
        String x = (String) en.next();
        String f[] = x.split( " " );
        //  THE fix *************
        if (f.length == 1)
            mailhost = f[0];
        else if ( f[1].endsWith( "." ) )
            mailhost = f[1].substring( 0, (f[1].length() - 1));
        else
            mailhost = f[1];
        //  THE fix *************            
        res.add( mailhost );
     }
     return res;
     }

   public static boolean isAddressValid( String address ) {
     // Find the separator for the domain name
     int pos = address.indexOf( '@' );

     // If the address does not contain an '@', it's not valid
     if ( pos == -1 ) return false;

     // Isolate the domain/machine name and get a list of mail exchangers
     String domain = address.substring( ++pos );
     //     ArrayList mxList = null;
     mxList = null;
     try {
         mxList = getMX( domain );
     }
     catch (NamingException ex) {
        return false;
     }

     // Just because we can send mail to the domain, doesn't mean that the
     // address is valid, but if we can't, it's a sure sign that it isn't
     if ( mxList.size() == 0 ) return false;
     
     int mx;
     
     // Now, do the SMTP validation, try each mail exchanger until we get
     // a positive acceptance. It *MAY* be possible for one MX to allow
     // a message [store and forwarder for example] and another [like
     // the actual mail server] to reject it. This is why we REALLY ought
     // to take the preference into account.
     for ( mx = 0 ; mx == mxList.size() ; mx++ ) {
         boolean valid = false;
         try {
             int res;
             //
             Socket skt = new Socket( (String) mxList.get( mx ), 25 );
             BufferedReader rdr = new BufferedReader
                ( new InputStreamReader( skt.getInputStream() ) );
             BufferedWriter wtr = new BufferedWriter
                ( new OutputStreamWriter( skt.getOutputStream() ) );

             res = hear( rdr );
             if ( res != 220 ) throw new Exception( "Invalid header" );
             say( wtr, "EHLO rgagnon.com" );

             res = hear( rdr );
             if ( res != 250 ) throw new Exception( "Not ESMTP" );

             // validate the sender address              
             say( wtr, "MAIL FROM: <tim@orbaker.com>" );
             res = hear( rdr );
             if ( res != 250 ) throw new Exception( "Sender rejected" );

             say( wtr, "RCPT TO: <" + address + ">" );
             res = hear( rdr );

             // be polite
             say( wtr, "RSET" ); hear( rdr );
             say( wtr, "QUIT" ); hear( rdr );
             if ( res != 250 )
                throw new Exception( "Address is not valid!" );

             valid = true;
             rdr.close();
             wtr.close();
             skt.close();
         }
         catch (Exception ex) {
           // Do nothing but try next host
           ex.printStackTrace();
         }
         finally {
           if ( valid ) return true;
         }
     }
     return false;
     }

   public static void main( String args[] ) {
     String testData[] = {
         "real@rgagnon.com",
         "you@acquisto.net",
         "fail.me@nowhere.spam", // Invalid domain name
         "arkham@bigmeanogre.net", // Invalid address
         "nosuchaddress@yahoo.com", // Failure of this method
         "v_its@yahoo.com"
         };

     for ( int ctr = 0 ; ctr < testData.length ; ctr++ ) {
        System.out.println( testData[ ctr ] + " is valid? " +
              isAddressValid( testData[ ctr ] ) );
     }
     return;
     }
} 
