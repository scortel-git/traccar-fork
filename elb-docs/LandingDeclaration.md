## Orbcomm пакет с добавен UniqueId

```02B740163031343335343333534B59393630415350565039326C424A55654E67646D75796C72432D510108803800000000002200000000210401000000000000016E0000000000C21E120B000B00CE38120B348C27009389190000005A0100000000000000001A05382C31383B337803```

| Field            | Type    | Bytes           | Note                                               | Version FVMS-M  | Additional Content                        |
|------------------|---------|-----------------|----------------------------------------------------|-----------------|-------------------------------------------|
| STX              | Byte    | 1               |                                                    |                 |                                           |
| Sequense         | Byte    | 1               |                                                    |                 |                                           |
| Description      | Byte    | 1               |                                                    |                 |                                           |
| Content          | Byte    | 1               | Bit 1 – Full or Send on parts   0-full, 1-part     |                 |                                           |
| TripnumberCount  | Byte    | 1               | TripnumberCount                                    |                 |                                           |
| Tripnumber       | Byte[]  | TripnumberCount | Tripnumber                                         |                 |                                           |
| FCRecCount       | Byte    | 1               | FCRec         Count                                |                 |                                           |
| FCRec            | FCRec   | FCRecCount      | FCRec                                              |                 |                                           |
| Pos              | PosRec  | ~               | Date/ time, position                               |                 |                                           |
| Sens             | SensRec | 9               | Current state of sensors                           |                 |                                           |
| LDFGList         | Char[]  | 21              | List ot fg->seq and geareye                        |                 | This field is implemented from v. 3.0.0.8 |
| StartUnloadingDT | Uint32  | 4               | Timestamp since 2018.01.01 00:00:00 UTC in seconds | Not implemented |                                           |
| EndUnloadingDT   | Uint32  | 4               | Timestamp since 2018.01.01 00:00:00 UTC in seconds | Not implemented |                                           |

LDRec current:

| Field            | Type    | Bytes           | Note                                               | Version FVMS-M  | Additional Content                        |
|------------------|---------|-----------------|----------------------------------------------------|-----------------|-------------------------------------------|
| STX              | Byte    | 1               |                                                    |                 |                                           |
| Sequense         | Byte    | 1               |                                                    |                 |                                           |
| Description      | Byte    | 1               |                                                    |                 |                                           |
| Content          | Byte    | 1               | Bit 1 – Full or Send on parts   0-full, 1-part     |                 |                                           |
| TripnumberCount  | Byte    | 1               | TripnumberCount                                    |                 |                                           |
| Tripnumber       | Byte[]  | TripnumberCount | Tripnumber                                         |                 |                                           |
| FCRecCount       | Byte    | 1               | FCRec         Count                                |                 |                                           |
| FCRec            | FCRec   | FCRecCount      | FCRec                                              |                 |                                           |
| Pos              | PosRec  | ~               | Date/ time, position                               |                 |                                           |
| Sens             | SensRec | 9               | Current state of sensors                           |                 |                                           |
| LDFGList         | Char[]  | 21              | List ot fg->seq and geareye                        |                 | This field is implemented from v. 3.0.0.8 |

FCRec:

| Field            | Type   | Bytes | Note                                                               | Version FVMS-M                      | Additional Content |
|------------------|--------|-------|--------------------------------------------------------------------|-------------------------------------|--------------------|
| Content          | Byte   | 1     | Bit 7 -- add at bit 5, 6, 7 - Landing dec (FVMS v. 04.2021.)       | 3.0.0.8                             |                    |
|                  |        |       | Bit 6 -- additionl catch/ not targeted (приулов - FVMS v. 3.0.0.8) |                                     |                    |
|                  |        |       | Bit 5 -- BMS or LSC (FVMS v. 3.0.0.8)                              |                                     |                    |
|                  |        |       | Bit 4 -- rec is deleted at client side                             |                                     |                    |
|                  |        |       | Bit 3 -- Discarded Data                                            |                                     |                    |
|                  |        |       | Bit 2 -- Data Correction                                           |                                     |                    |
|                  |        |       | Bit 1 -- DiscardedRecCrDT -- exclude/include                       |                                     |                    |
|                  |        |       | Bit 0 - Not in use                                                 |                                     |                    |
| Srec             | Srec   | 2~7   | Species description record                                         |                                     |                    |
| Presentation     | Byte   | 1     | Sequence number from list presentations (0-255)                    |                                     |                    |
| Condition        | Byte   | 1     | Sequence number from list conditions (0-255)                       |                                     |                    |
| QuantityType     | Byte   | 1     | Sequence number from list quantitytype (0-255)                     |                                     |                    |
| Discarded        | Uint32 | 4     | Weight or units                                                    |                                     |                    |
| DiscardedCount   | Uint16 | 2     | Count of units                                                     |                                     |                    |
| DiscardedType    | Byte   | 1     | Sequence number from list presentations (0-255)                    |                                     |                    |
| Weight           | Uint32 | 4     | Weight in kilograms                                                |                                     |                    |
| WeightCount      | Uint16 | 2     | Count of units                                                     |                                     |                    |
| CreationDT       | Uint32 | 4     | Timestamp since 2018.01.01 00:00:00 UTC in seconds                 |                                     |                    |
| DiscardedRecCrDT | Uint32 | 4     | Timestamp since 2018.01.01 00:00:00 UTC in seconds                 |                                     |                    |
| SizeGroup        | Byte   | 1     | Size group of specific species                                     |                                     |                    |
| QTCount          | Uint16 | 2     | QuantityType count of storage containers                           | 3.0.0.8 and newer                   |                    |
| QTTara           | Uint16 | 2     | Weight "Tara" of storage container                                 | Planned for 3.0.0.8 not implemented |                    |
| QTBruto          | Uint16 | 2     | Weight "Bruto" of storage container                                | Planned for 3.0.0.8 not implemented |                    |
| Packet length    |        |       |                                                                    |                                     |                    |

SRec:

| Field         | Type   | Bytes | Note                                                                |
|---------------|--------|-------|---------------------------------------------------------------------|
| Content       | Byte   | 1     | Bit 7: 0 - minimal information, 1 - full information                |
|               |        |       | Bit 6: Not in use (removed)                                         |
|               |        |       | Bit 5: Not in use                                                   |
|               |        |       | Bit 4: Not in use                                                   |
|               |        |       | Bit 3: Not in use                                                   |
|               |        |       | Bit 2: Data correction                                              |
|               |        |       | 0 - no correction of data                                           |
|               |        |       | 1 - with correction                                                 |
|               |        |       | Bit 1: 0 - DiscardedRecCrDT - empty, 1 - DiscardedRecCrDT with data |
|               |        |       | Bit 0: Not in use                                                   |
| species       | Uint16 | 2     | Sequence number from list species (0-255)                           |
| Price         | Float  | 4     | Price                                                               |
| Currency      | Byte   | 1     | Sequence number from list currencies (0-255)                        |
| CreationDT    | Uint32 | 4     | Timestamp since 2018.01.01 00:00:00 UTC in seconds                  |
| CreationDT    | Uint32 | 4     | Timestamp since 2018.01.01 00:00:00 UTC in seconds                  |
| Packet length |        | 11~16 |                                                                     |


| Field            | Type    | Bytes | Note                                                               | Version FVMS-M                                                      | Additional Content                             |
|------------------|---------|-------|--------------------------------------------------------------------|---------------------------------------------------------------------|------------------------------------------------|
| STX              | Byte    | 1     |                                                                    |                                                                     |                                                |
| Sequense         | Byte    | 1     |                                                                    |                                                                     |                                                |
| Description      | Byte    | 1     |                                                                    |                                                                     |                                                |
| Content          | Byte    | 1     | Bit 1 – Full or Send on parts   0-full, 1-part                     |                                                                     |                                                |
| Tripnumber       | Byte[]  | 22    | Tripnumber                                                         |                                                                     |                                                |
| Content          | Byte    | 1     | Bit 7 - 0: Fishing JFRec excluded, 1: Included                     | [3.0.0.12 -- bit0 = 1 -- JFRec was deleted. Not implemented]{.mark} | 16                                             |
|                  |         |       | Bit 6 - 25.06.2019 v.1.0.0.8 - 0: Opened, 1: Closed                |                                                                     | B7                                             |
|                  |         |       | Bit 5 - 0: Excluded EFO, 1: Included EFO                           |                                                                     | 40                                             |
|                  |         |       | Bit 4 - Not in use                                                 |                                                                     |                                                |
|                  |         |       | Bit 3 - Not implemented (Auto / Manual)                            |                                                                     |                                                |
|                  |         |       | Bit 2 - Data correction                                            |                                                                     |                                                |
|                  |         |       | Bit 1 - 0: DiscardedRecCrDT empty, 1: with data                    |                                                                     |                                                |
|                  |         |       | Bit 0 - Not in use                                                 |                                                                     |                                                |
| StartFO          | SE      | 13\   | Start of fishing FORec                                             |                                                                     |                                                |
| EndFO            | SE      | 12\   | End of fishing FORec                                               |                                                                     |                                                |
| JFRec            | JFRec   | \~    | Joint vessels information                                          |                                                                     |                                                |
| FCRecsCount      | Byte    | 1     | Count of FCRecs records                                            |                                                                     |                                                |
| FCRecs           | FCRe    | 13\   | List of fishing catch records                                      |                                                                     |                                                |
| CreationDT       | U       | 4     | Timestamp since 2018.01.01 00:00:00 UTC                            |                                                                     |                                                |
|                  |         |       | in seconds                                                         |                                                                     |                                                |
| DiscardedRecCrDT | U       | 4     | Timestamp since 2018.01.01 00:00:00 UTC                            |                                                                     |                                                |
|                  |         |       | in seconds                                                         |                                                                     |                                                |
| Tripnumber       | Byt     | \~    | Tripnumber                                                         |                                                                     | 3031343335343335534B59393630415350565039326C42 |
| Sens             | Se      | 9     | IDP Sensors data                                                   |                                                                     |                                                |
| [Packet length]  |
| Content          | Byte    | 1     | Bit 7 -- add at bit 5, 6, 7 - Landing dec (FVMS v. 04.2021.)       | 3.0.0.8                                                             |                                                |
|                  |         |       | Bit 6 -- additionl catch/ not targeted (приулов - FVMS v. 3.0.0.8) |                                                                     |                                                |
|                  |         |       | Bit 5 -- BMS or LSC (FVMS v. 3.0.0.8)                              |                                                                     |                                                |
|                  |         |       | Bit 4 -- rec is deleted at client side                             |                                                                     |                                                |
|                  |         |       | Bit 3 -- Discarded Data                                            |                                                                     |                                                |
|                  |         |       | Bit 2 -- Data Correction                                           |                                                                     |                                                |
|                  |         |       | Bit 1 -- DiscardedRecCrDT -- exclude/include                       |                                                                     |                                                |
|                  |         |       | Bit 0 - Not in use                                                 |                                                                     |                                                |
| Srec             | Srec    | 2~7   | Species description record                                         |                                                                     |                                                |
| Presentation     | Byte    | 1     | Sequence number from list presentations (0-255)                    |                                                                     |                                                |
| Condition        | Byte    | 1     | Sequence number from list conditions (0-255)                       |                                                                     |                                                |
| QuantityType     | Byte    | 1     | Sequence number from list quantitytype (0-255)                     |                                                                     |                                                |
| Discarded        | Uint32  | 4     | Weight or units                                                    |                                                                     |                                                |
| DiscardedCount   | Uint16  | 2     | Count of units                                                     |                                                                     |                                                |
| DiscardedType    | Byte    | 1     | Sequence number from list presentations (0-255)                    |                                                                     |                                                |
| Weight           | Uint32  | 4     | Weight in kilograms                                                |                                                                     |                                                |
| WeightCount      | Uint16  | 2     | Count of units                                                     |                                                                     |                                                |
| CreationDT       | Uint32  | 4     | Timestamp since 2018.01.01 00:00:00 UTC in seconds                 |                                                                     |                                                |
| DiscardedRecCrDT | Uint32  | 4     | Timestamp since 2018.01.01 00:00:00 UTC in seconds                 |                                                                     |                                                |
| SizeGroup        | Byte    | 1     | Size group of specific species                                     |                                                                     |                                                |
| QTCount          | Uint16  | 2     | QuantityType count of storage containers                           | 3.0.0.8 and newer                                                   |                                                |
| QTTara           | Uint16  | 2     | Weight "Tara" of storage container                                 | Planned for 3.0.0.8 not implemented                                 |                                                |
| QTBruto          | Uint16  | 2     | Weight "Bruto" of storage container                                | Planned for 3.0.0.8 not implemented                                 |                                                |
| Packet length    |         |       |                                                                    |                                                                     |                                                |
| Pos              | PosRec  | ~     | Date/ time, position                                               |                                                                     |                                                |
| Sens             | SensRec | 9     | Current state of sensors                                           |                                                                     |                                                |
| LDFGList         | Char[]  | 21    | List ot fg->seq and geareye                                        |                                                                     | This field is implemented from v. 3.0.0.8      |
| StartUnloadingDT | Uint32  | 4     | Timestamp since 2018.01.01 00:00:00 UTC in seconds                 | Not implemented                                                     |                                                |
| EndUnloadingDT   | Uint32  | 4     | Timestamp since 2018.01.01 00:00:00 UTC in seconds                 | Not implemented                                                     |                                                |
| Packet length    |         |       |                                                                    |                                                                     |                                                |


FORec:

| Field            | Type  | Bytes | Note                                                | Version FVMS-M                                                      | Additional Content                             |
|------------------|-------|-------|-----------------------------------------------------|---------------------------------------------------------------------|------------------------------------------------|
| Content          | Byte  | 1     | Bit 7 - 0: Fishing JFRec excluded, 1: Included      | [3.0.0.12 -- bit0 = 1 -- JFRec was deleted. Not implemented]{.mark} | 16                                             |
|                  |       |       | Bit 6 - 25.06.2019 v.1.0.0.8 - 0: Opened, 1: Closed |                                                                     | B7                                             |
|                  |       |       | Bit 5 - 0: Excluded EFO, 1: Included EFO            |                                                                     | 40                                             |
|                  |       |       | Bit 4 - Not in use                                  |                                                                     |                                                |
|                  |       |       | Bit 3 - Not implemented (Auto / Manual)             |                                                                     |                                                |
|                  |       |       | Bit 2 - Data correction                             |                                                                     |                                                |
|                  |       |       | Bit 1 - 0: DiscardedRecCrDT empty, 1: with data     |                                                                     |                                                |
|                  |       |       | Bit 0 - Not in use                                  |                                                                     |                                                |
| StartFO          | SE    | 13\   | Start of fishing FORec                              |                                                                     |                                                |
| EndFO            | SE    | 12\   | End of fishing FORec                                |                                                                     |                                                |
| JFRec            | JFRec | \~    | Joint vessels information                           |                                                                     |                                                |
| FCRecsCount      | Byte  | 1     | Count of FCRecs records                             |                                                                     |                                                |
| FCRecs           | FCRe  | 13\   | List of fishing catch records                       |                                                                     |                                                |
| CreationDT       | U     | 4     | Timestamp since 2018.01.01 00:00:00 UTC             |                                                                     |                                                |
|                  |       |       | in seconds                                          |                                                                     |                                                |
| DiscardedRecCrDT | U     | 4     | Timestamp since 2018.01.01 00:00:00 UTC             |                                                                     |                                                |
|                  |       |       | in seconds                                          |                                                                     |                                                |
| Tripnumber       | Byt   | \~    | Tripnumber                                          |                                                                     | 3031343335343335534B59393630415350565039326C42 |
| Sens             | Se    | 9     | IDP Sensors data                                    |                                                                     |                                                |
| [Packet length]  |       |       |                                                     |                                                                     |                                                |




SEFORec:

| Field                     | Type | Bytes | Note                                                                                                     | Version | Additional Content |
|---------------------------|------|-------|----------------------------------------------------------------------------------------------------------|---------|--------------------|
| ProtocolVersion           | Byte | 1     | No field -- 3.0.0.7                                                                                      | 3.0.0.9 |                    |
| Content                   | Byte | 1     | Bit 7 - 0: Normal fishing operation, 1: Set fishing gear                                                 | 3.0.0.8 | 00                 |
|                           |      |       | Bit 6 - 0: Excluded statistical and economic zone field, 1: Included statistical and economic zone field |         |                    |
|                           |      |       | Bit 5 - Status generation of record, 0: Auto generated, 1: Manual generated                              |         |                    |
|                           |      |       | Bit 4 - Position GPS part, 0: Excluded, 1: Included                                                      |         |                    |
|                           |      |       | Bit 3 - Start/End FO, 0: Start FO, 1: End FO                                                             |         |                    |
|                           |      |       | Bit 2 - Data Correction, 0: No, 1: Yes                                                                   |         |                    |
|                           |      |       | Bit 1 - 0: DiscardedRecCrDT empty, 1: with data                                                          |         |                    |
|                           |      |       | Bit 0 - 0: Normal fo/gear shot, 1: Retrieve fishing gear                                                 |         |                    |
| FGRecsCount               | Byte | 1     | Count of FGRecs records                                                                                  |         |                    |
| FGRecs                    | FGRe | \~    | List of Fishing gear records                                                                             |         |                    |
| Economic zone             | U    | 2     | Sequence number from list economic zones (0-255)                                                         |         |                    |
|                           |      |       | Can be omitted, and the calculation to be performed on the server based on geographic coordinates        |         |                    |
| Statistical or other zone | U    | 2     | Sequence number from list statistical or other zones (0-255)                                             |         |                    |
|                           |      |       | Can be omitted, and the calculation to be performed on the server based on geographic coordinates        |         |                    |
| Position                  | P    | 18    | GPS position -- there is a Timestamp field which is used for time checks                                 |         |                    |
| FODepth                   | Byte | 1     | Depth in meters * 10 of start and end of FO                                                              |         |                    |
| CreationDT                | U    | 4     | Timestamp since 2018.01.01 00:00:00 UTC in seconds                                                       |         |                    |
| DiscardedRecCrDT          | U    | 4     | Timestamp since 2018.01.01 00:00:00 UTC in seconds                                                       |         |                    |
| Sens                      | Se   | 9     | IDP Sensors data                                                                                         |         |                    |
| Pos                       | P    |       | Position                                                                                                 |         |                    |
| [Packet length]           |      |       |                                                                                                          |         |                    |


| Field          | Type   | Bytes | Note                                             | Version FVMS-M | Additional Content                                  |
|----------------|--------|-------|--------------------------------------------------|----------------|------------------------------------------------------|
| Content        | Byte   | 1     | Bit 7 - 0: Fishing JFRec excluded, 1: Included  | [3.0.0.12 -- bit0 = 1 -- JFRec was deleted. Not implemented]{.mark} | 16                                               |
|                |        |       | Bit 6 - 25.06.2019 v.1.0.0.8 - 0: Opened, 1: Closed |                | B7                                               |
|                |        |       | Bit 5 - 0: Excluded EFO, 1: Included EFO         |                | 40                                               |
|                |        |       | Bit 4 - Not in use                               |                |                                                   |
|                |        |       | Bit 3 - Not implemented (Auto / Manual)          |                |                                                   |
|                |        |       | Bit 2 - Data correction                          |                |                                                   |
|                |        |       | Bit 1 - 0: DiscardedRecCrDT empty, 1: with data  |                |                                                   |
|                |        |       | Bit 0 - Not in use                               |                |                                                   |
| StartFO        | SE     | 13\   | Start of fishing FORec                          |                |                                                   |
| EndFO          | SE     | 12\   | End of fishing FORec                            |                |                                                   |
| JFRec          | JFRec  | \~    | Joint vessels information                       |                |                                                   |
| FCRecsCount    | Byte   | 1     | Count of FCRecs records                         |                |                                                   |
| FCRecs         | FCRe   | 13\   | List of fishing catch records                   |                |                                                   |
| CreationDT     | U      | 4     | Timestamp since 2018.01.01 00:00:00 UTC        |                |                                                   |
|                |        |       | in seconds                                       |                |                                                   |
| DiscardedRecCrDT | U    | 4     | Timestamp since 2018.01.01 00:00:00 UTC        |                |                                                   |
|                |        |       | in seconds                                       |                |                                                   |
| Tripnumber     | Byt    | \~    | Tripnumber                                       |                | 3031343335343335534B59393630415350565039326C42     |
| Sens           | Se     | 9     | IDP Sensors data                                 |                |                                                   |
| [Packet length] |        |       |                                                  |                |                                                   |
