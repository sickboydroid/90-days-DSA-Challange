# Networking

- [Networking](#networking)
  - [Overview](#overview)
  - [Working with URLs](#working-with-urls)

## Overview

- We are working on **application** layer
- `java.net` package's classes provide system-independent network communication
- `TCP (Transmission Control Protocol)` is a connection-based protocol that provides a reliable flow of data between two computers.
- `UDP (User Datagram Protocol)` is a protocol that sends independent packets of data, called datagrams, from one computer to another with no guarantees about arrival. UDP is not connection-based like TCP.
- Computer on internet is identified by 32 bit integer called `ip-address`.
- Ports are identified by 16 bit integer. Ports are used py tcp and udp to deliver packets to right application

  - port numbers are from 0 to 65,535
  - 0-1023 are reserved for services like http and ftp (called **well-known ports**)

- `URL`, `URLConnection`, `Socket`, `ServerSocket` classes use TCP
- `DatagramPacket`, `DatagramSocket`, `MulticastSocket` classes use UDP

## Working with URLs

`https://www.google.com:80/page1.html`

- for encoding a url (e.g converting google.com/hello world to google.com/hello%20world) you can use constructors of uri

- `http://example.com:80/docs/books/tutorial/index.html?name=networking#DOWNLOADING`
  - protocol = http
  - authority = example.com:80
  - host = example.com
  - port = 80
  - path = /docs/books/tutorial/index.html
  - query = name=networking
  - filename = /docs/books/tutorial/index.html?name=networking
  - ref = DOWNLOADING
