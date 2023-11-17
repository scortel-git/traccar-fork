## Орбком пакет - Payload още не е преескепнат

***Raw packet***

```python3
027c31f0e88fef0a361020ed73ef0aed73ef0ab00a2710207c6f15102010201020361020ed73ef0a16656a39333251324c5745533739456a393149425072518f102010201020102010201020102012273303
```

```
		[00]    0x02	byte
		[01]    0x7c	byt
		[02]    0x31	byte
		[03]    0xf0	byte
		[04]    0xe8	byte
		[05]    0x8f	byte
		[06]    0xef	byte
		[07]    0x0a	byte
		[08]    0x36	byte
		[09]    0x10	byte
		[10]    0x20	byte
		[11]    0xed	byte
		[12]    0x73	byte
		[13]    0xef	byte
		[14]    0x0a	byte
		[15]    0xed	byte
		[16]    0x73	byte
		[17]    0xef	byte
		[18]    0x0a	byte
		[19]    0xb0	byte
		[20]    0x0a	byte
		[21]    0x27	byte
		[22]    0x10	byte
		[23]    0x20	byte
		[24]    0x7c	byte
		[25]    0x6f	byte
		[26]    0x15	byte
		[27]    0x10	byte
		[28]    0x20	byte
		[29]    0x10	byte
		[30]    0x20	byte
		[31]    0x10	byte
		[32]    0x20	byte
		[33]    0x36	byte
		[34]    0x10	byte
		[35]    0x20	byte
		[36]    0xed	byte
		[37]    0x73	byte
		[38]    0xef	byte
		[39]    0x0a	byte
		[40]    0x16	byte
		[41]    0x65	byte
		[42]    0x6a	byte
		[43]    0x39	byte
		[44]    0x33	byte
		[45]    0x32	byte
		[46]    0x51	byte
		[47]    0x32	byte
		[48]    0x4c	byte
		[49]    0x57	byte
		[50]    0x45	byte
		[51]    0x53	byte
		[52]    0x37	byte
		[53]    0x39	byte
		[54]    0x45	byte
		[55]    0x6a	byte
		[56]    0x39	byte
		[57]    0x31	byte
		[58]    0x49	byte
		[59]    0x42	byte
		[60]    0x50	byte
		[61]    0x72	byte
		[62]    0x51	byte
		[63]    0x8f	byte
		[64]    0x10	byte
		[65]    0x20	byte
		[66]    0x10	byte
		[67]    0x20	byte
		[68]    0x10	byte
		[69]    0x20	byte
		[70]    0x10	byte
		[71]    0x20	byte
		[72]    0x10	byte
		[73]    0x20	byte
		[74]    0x10	byte
		[75]    0x20	byte
		[76]    0x10	byte
		[77]    0x20	byte
		[78]    0x12	byte
		[79]    0x27	byte
		[80]    0x33	byte
		[81]    0x03	byte
```

***Пакет за анализ след премахване на преескепването***

```python3
027c31f0e88fef0a3600ed73ef0aed73ef0ab00a27007c6f150000003600ed73ef0a16656a39333251324c5745533739456a393149425072518f0000000000000012
```

***STX - 1 byte***

    [00]	0x02	byte
***Sequence - 1 byte***

    [00]	0x7c	byte
***Description (Mask) - 1 byte***

    [00]	0x31	byte 
***Content - 1 byte***

    [00]	0xf0	byte 
***EstimatedTime 4 bytes; Прогнозно време на прибиране  = 183472104***  

***Timestamp since 2018.01.01 00:00:00 UTC in seconds***

    [01]	0xe8	byte 
    [02]	0x8f	byte
    [03]	0xef	byte
    [04]	0x0a	byte
***Порт на прибиране 2 bytes;  = 54***

    [05]	0x36	byte
    [06]	0x00	byte
***Край на риболова 4 bytes; 183464941***

***Timestamp since 2018.01.01 00:00:00 UTC in seconds***

    [07]	0xed	byte 
    [08]	0x73	byte
    [09]	0xef	byte
    [10]	0x0a	byte
    
***POSRec - 16 bytes; 183464941*** 

***Timestamp since 2018.01.01 00:00:00 UTC in seconds***

    [11]	0xed	byte 
    [12]	0x73	byte        
    [13]	0xef	byte
    [14]	0x0a	byte
***Latitude 4 bytes; 2558640***

    [15]	0xb0	byte
    [16]	0x0a	byte
    [17]	0x27	byte
    [18]	0x00	byte
***Longitude 4 bytes; 1404796***

    [19]	0x7c	byte  
    [20]	0x6f	byte
    [21]	0x15	byte
    [22]	0x00	byte
***Speed 2 bytes;***

    
    10-ти от knots
    var knots = (double)tenths_of_knots / 10;
    var kmh = knots * 1.852;
    var res = Math.Round(kmh);
    return (ushort)res;

    [23]	0x00	byte 
    [24]	0x00	byte
***Course 2 bytes; = 54***

    [25]	0x36	byte - course 54
    [26]	0x00	byte
***Creation DT 4 bytes; 183464941   2023-10-25 10:29:01***

***Timestamp since 2018.01.01 00:00:00 UTC in seconds***    

    [27]	0xed	byte
    [28]	0x73	byte
    [29]	0xef	byte
    [30]	0x0a	byte
***Trip Number Lenght 1 byte;  = 22 - UTF8***

    [31]	0x16	byte 
    
***Trip number as String Lenght bytes; ej932Q2LWES79Ej91IBPrQ***

    [32]	0x65	byte
    [33]	0x6a	byte
    [34]	0x39	byte
    [35]	0x33	byte
    [36]	0x32	byte
    [37]	0x51	byte
    [38]	0x32	byte
    [39]	0x4c	byte
    [40]	0x57	byte
    [41]	0x45	byte
    [42]	0x53	byte
    [43]	0x37	byte
    [44]	0x39	byte
    [45]	0x45	byte
    [46]	0x6a	byte
    [47]	0x39	byte
    [48]	0x31	byte
    [49]	0x49	byte
    [50]	0x42	byte
    [51]	0x50	byte
    [52]	0x72	byte
    [53]	0x51	byte
***Analog 1 4 bytes; 143***

    [54]	0x8f	byte 
    [55]	0x00	byte
    [56]	0x00	byte
    [57]	0x00	byte
***Analog 2 4 bytes; 0***
    
    [58]	0x00	byte 
    [59]	0x00	byte
    [60]	0x00	byte
    [61]	0x00	byte
***DIO 1 byte; 18***

    [62]	0x12	byte

22840166471704d53534868596b794a594d353134746966796718802e10201020102010201020221020102010201020214102210201020102010201020102018d910201020102010206f29b10201020102060f49b6ad72610208c58191020471020d61020102010201020102010201020102010201a4382c363b2fb53