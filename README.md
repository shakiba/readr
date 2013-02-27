# readr

Unofficial experimental Google Reader API for Java

#### Design

There is a class for each request type and there are two global request super type: API0 and Atom.
You can view list of currently available [API0 requests](src/main/java/x/readr/api0/req/) and [Atom requests](src/main/java/x/readr/atom/req/).

To execute a request first you need to create a new request object and provide required and optional parameters.

    StreamContent req = new StreamContent(StreamId.feed("http://example.com/rss"));

To execute it you need a connection. There two type of connections: simple and authenticated. Some requests require authenticated connection to execute.

    ApacheHttpClient4Connection simpleConn = new ApacheHttpClient4Connection(2);
    // or
    OAuthSribeConnection oauthConn = new OAuthSribeConnection(...);
    
Then to execute:

    Api0Stream stream = req.execute(simpleConn);
    
Returned objects contain API responses mapped to Java classes and fields:

    public class Api0Stream {
        public String direction;
        public StreamId id;
        public String title;
        public String description;
        public String continuation;
        public List<Api0Link> self;
        public List<Api0Link> alternate;
        public long updated;
        public List<Api0Item> items;
    }
    
Common requests parameters are encapsulated in param-set classes and are added to request classes as public final fields:

     req.psStream.setContinuation(...);
     
#### Credit/Acknowledgments

[google-reader-api](http://code.google.com/p/google-reader-api/) documentation has been extensively used.

A comprehensive documentation is also available here: http://undoc.in/googlereader.html


#### TODO

Test classes are incomplete.
