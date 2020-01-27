# Notes about DNS

Converting a hostname (e.g. www.google.com) to an IP address.

4 DNS servers involved with loading a webpage:
1. DNS recursor, can be thought of as a librarian who is asked to find a particular book. Server designed to receive queries from client machines, responsible for making additional requests to satisfy the client's query.
2. Root nameserver, first step in resolving host names into IP addresses. Can be thought of like an index in a library that points to different racks of books.
3. TLD namesever, the top level domain server can be thought of as a specific rack of books in a library. It hosts the last portion of a hostname, e.g. .com for google.com
4. Authoritative nameserver, can be thought of as a dictionary in the rack of books in which a specific name can be translated into its definition. This is the last stop in the nameserver query. It will
return the IP address for the requested hostname back to the DNS recursor that made the initial request.

So the analogy looks like this:
1. You need to find a particular book.
2. You ask the librarian (DNS recursor)
3. The librarian looks at the index (Root nameserver) which points to a specific rack of books
4. The librarian goes to the specific rack of books (TLD nameserver)
5. The librarian looks at the dictionary in this rack of books to find translate the book name into its exact location.
6. Librarian returns the book.

The last bit falls down a bit (doesn't really make much sense to look in the dictionary when we're looking for a specific book...).

## Difference between authoritative nameserver and a recursive DNS resolver
- Both refer to groups of servers that perform jobs in pipeline of a DNS query.
- Recursive resolver is at the start of the pipeline and authoritative is at the end.

### Recursive DNS resolver
- Makes multiple requests to different servers until it reaches the authoritative nameserver or times out (if the record wasn't found). They do not always need to make these requests,
as caching is used to short-circuit some of the requests.

### Authoritative nameserver
- Actually holds the DNS records
- Can satisfy queries from its own records without needing to query another source as it is the final source of truth for certain DNS records.
- When the hostname contains a subdomain (e.g. foo.google.com), another server is added to the sequence after the authoritative nameserver which is responsible for serving
the subdomain's CNAME.

## DNS lookup
Note: often DNS lookup information will be cached inside the querying computer or remotely on DNS infrastructure. There are typically 8 steps, however when data is cached steps will be skipped,
making the process much quicker.
1. User types example.com into their browser. Query sent by browser to a DNS recursive resolver (DRR).
2. DRR queries a root nameserver
3. Root nameserver responds to the DRR with the address of a TLD nameserver which stores the information for its domains. For example.com, the TLD is .com.
4. The DRR then makes another request to the TLD nameserver.
5. The TLD nameserver responds with an IP address of the domain's nameserver
6. The DRR makes another request to the domains nameserver
7. The IP address for example.com is then returned to the DRR from the nameserver.
8. The DNS resolver then responds to the web browser with the IP address of the domain requested initially.

Once these steps have occurred (an IP address has been obtained for a hostname).

9. Browser makes a HTTP request to the IP address
10. The server at that IP returns the webpage to be rendered in the browser.


## DNS records

DNS records are instructions that live on authoritative DNS servers and provide information about a domain, including what IP address is associated with that domain and how to handle requests for that domain.
These records are text files that tell the DNS server what to do.

All records have a TTL (time-to-live) and indicates how often a DNS server will refresh that record.

Most common types of record are:
- A, holds the IP address of the domain
- CNAME, forwards one domain or subdomain to another domain
- MX, directs mail to an email server
- NS, name server for a DNS entry


### A record
Stands for 'Address' record. It indicates the IP address for the given domain. They only hold ipv4 addresses, ipv6 are AAAA records.

### CNAME record
Stands for 'canonical name' record. It's used in lieu of an A record when a domain or subdomain is an alias of another domain. For example imagine www.example.com has a CNAME record
with a value of example.com, when a DNS server hits the records for www.example.com, it will trigger a request to example.com's records.

These records must ALWAYS point to a hostname, and never an IP address.




Useful links:
- https://www.cloudflare.com/learning/dns/what-is-dns/