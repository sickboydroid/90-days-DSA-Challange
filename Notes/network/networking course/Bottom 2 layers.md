# Bottom 2 Layers

1. Ethernet cable has 4 pairs of wires (total 8 different wires). 1 bod = 1 symbol/sec. We send info over copper wire by varying volatages.
   - 1 pair sends and other pair receives. Remaining two pairs are used rarely
2. Fiber Optics works on the principle of total internal reflection. Here as well we can say 0 is light on and 1 is light off.
3. Radio Waves can also be used for sending information. Shifting phases of waves can help in sending 0s and 1s.

---

1. Clock synchronization is very important while transferring data over a network
2. Clock slip is the phenomenon when clocks are out of sync and we read some extra or some less bits which corrupt our message
3. One way to sync clocks is using GPS. GPS's have atomic clocks which are pretty accurate.
4. Second solution is to embed atomic clocks inside systems
5. Other solution is to send a clock signal for syncing the clocks of sender and receiver
6. In practice, we mix clock with data.
    - For example if 0V was 0 and 5V was 1 before, now we are sayin that transition from 0V to 5V is 1 and transition from 5V to 0V is 0
    - So even if the receivers clock isn't perfectly synced it can still read the incoming data properly
   
 - It is called Manchester coding
  
---

## Point to Point data link

Data is shared b/w two computers connected at the two ends of an ethernet cable or fibre optic cable.

**How does the receiver know the byte boundry?**
    *Framing* is the answer.

### HDLC framing format

In HDLC (High LEvel Data Link Control) protocol we use 0111110 as a flag to indicate that a new frame starts after it. So the receiver knows that the first byte starts after this flag

If the data contains five 1's in consecutive, **bit stuffing** is performed. So in order to prevent the receiver from mistakingly mis-interpretting the data as flag, 0 is inserted after the four 1's and the receiver is said to ignore it

> Ethernet has different mecahnism. Inter-frame gap (IFG) is a gap when there is no data sent (0V) and then it sends **Preamble** which is a 56 bits long alternating 0's and 1's.
>    - Something like this -------------1010101010...101011
>    - The last 11 informs the receiver that after this 11 the upcoming bit is data

> Large frames mean efficiency but if receiver misreads some byte then it will misread everything after that until new frame starts. Ethernet Frame sizes vary from 64-1500 bytes usually.

### PPP framing format

PPP uses the same HDLC framing. There is flag byte (0111110) at the beginning.

We don't need address field as it Point-to-Point data link. So mostly address is set to 0xff

Also control has no use and set to 0x03

Protocol, Payload, and FCS  are equivalent to Ether Type, Payload and Frame Check Seq of ethernet

![PPP format]


---

## Multipoint/Broadcast data links

#### Ethernet framing format for Multipoint data links

For example multiple computers connected via radio waves or ethernet cable

![Ethernet]()

- Ethernet/MAC addresses are builtin in all Ethernet interfaces
- MAC address of receiving computer is what goes into Destination address and MAC address of sending computer is what goes into Source Address
  - Setting destination address to all ones (or ff:ff:ff...:ff in hex) informs that it is for every computer
- Ether Type gives us what type of data are we expecting in payload. In most cases it is going to be an IP packet.
- Last 4 bytes consist of Frame Check seq. It is a number computed on the basis of everything the frame contains (from dest addr to payload). It lets the receiver know if there has been any corruption of data along the way.

