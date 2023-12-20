**Innovation Norwar ST9100 Protocol v.4**

**Актуален към 04.2023г.**
> ***TFrame***

|             |           |           |                                           |
|-------------|-----------|-----------|-------------------------------------------|
| **Field**   | **Bytes** | **Value** | **Note**                                  |
| STX         | 1         | 0x02      | Start of transmission                     |
| Sequence    | 1         |           | Sequence number                           |
| Description | 1         |           | Description of frame                      |
| Data        | n         |           | Data                                      |
| CRC         | 2         |           | Checksum (from Sequence to Data included) |
| ETX         | 1         | 0x03      | End of transmission                       |

**Типове пакети (Packets types)**

Do not use numbers: 0x00,0x02,0x03 and 0x10 to avoid escaping

***TframeDescription***

|       |                                     |                                                                                                                                                                                                                         |
|-------|-------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| 0x00  |                                     | Not in use, Escaped protocol specific/ Не се използва, специфични за протокола                                                                                                                                          |
| 0x01  | Acknowledge                         | Ack                                                                                                                                                                                                                     |
| 0x02  | \-                                  | Not in use, escaped protocol specific (Start of frame)/ Не се използва, специфични за протокола (Начало на пакета)                                                                                                      |
| 0x03  | \-                                  | Not in use, escaped protocol specific (End of frame)/ Не се използва, специфични за протокола (Край на пакета)                                                                                                          |
| 0x04  |                                     | Not in use/ Не се използва                                                                                                                                                                                              |
| 0x05  |                                     | Not in use/ Не се използва                                                                                                                                                                                              |
| 0x06  | Text                                | Sending text message (server  mobile device)/ Изпращане на текст                                                                                                                                                       |
| 0x07  | LongText (separated)                | Sending long text (can be split into chunks)/ Изпращане на дълъг текст (може да е разделен на порции)                                                                                                                   |
| 0x08  | File (separated)                    | Sending file (can be split into chunks)/ Изпращане на файл (може да е разделен на порции)                                                                                                                               |
| 0x09  | TextWithGPS                         | Sending text message with GPS position (mobile device  server)/ Изпращане на тескт с географски позиции                                                                                                                |
| 0x0A  |                                     | Not in use/ Не се използва                                                                                                                                                                                              |
| 0x0B  |                                     | Not in use/ Не се използва                                                                                                                                                                                              |
| 0x0C  |                                     | Not in use/ Не се използва                                                                                                                                                                                              |
| 0x0D  |                                     | Not in use/ Не се използва                                                                                                                                                                                              |
| 0x0E  | Live Update                         | за инсталиране на нова версия на софтуера от разстояние                                                                                                                                                                 |
| 0x0F  |                                     | Not in use/ Не се използва                                                                                                                                                                                              |
| 0x10  | \-                                  | Not in use, escaped protocol specific/ Не се използва, специфични за протокола                                                                                                                                          |
| 0x11  | Get Software Version                | Get Software version (server  mobile device)/ Изискване на версия на мобилния софтуера                                                                                                                                 |
| 0x12  | Get Shell Version                   | Get Shell version (server  mobile device)/ Изискване на версия на мобилния шел                                                                                                                                         |
| 0x13  | Get Crew Info                       | Get Crew Info (server  mobile device)/ Изискване на данни за екипажа                                                                                                                                                   |
| 0x14  | Get Vessel Info                     | Get Vessel Info (server  mobile device)/ Изискване на данни за РК                                                                                                                                                      |
| 0x15  | Get Current Fishing Trip            | Get Current fishing trip (See 1.3.1.) (server  mobile device)/ Изискване на данни за текущия риболовен рейс                                                                                                            |
| 0x16  | Get Current Fishing day information | Get Current fishing day information (see 1.3.8. DIRec) (server  mobile device)/ Изискване на информация за текущия риболовен ден                                                                                       |
| 0x17  |                                     | Not in use/ Не се използва                                                                                                                                                                                              |
| 0x18  |                                     | Not in use/ Не се използва                                                                                                                                                                                              |
| 0х19  |                                     | Not in use/ Не се използва                                                                                                                                                                                              |
| 0x1A  |                                     | Not in use/ Не се използва                                                                                                                                                                                              |
| 0x1B  |                                     | Not in use/ Не се използва                                                                                                                                                                                              |
| 0x1C  |                                     | Not in use/ Не се използва                                                                                                                                                                                              |
| 0x1D  | Set Configuration                   | Setting configuration values (server  mobile device)/ Задаване на стойности за конфигуриране                                                                                                                           |
| 0x1E  | Get Configuration                   | Query the current configuration values (server  mobile device)/ Заявка за текущите стойности за конфигуриране                                                                                                          |
| 0x1F  |                                     | Not in use/ Не се използва                                                                                                                                                                                              |
| 0x20  |                                     | Not in use/ Не се използва                                                                                                                                                                                              |
| 0x21  | Software Version                    | Response of cmd 0x11 with software version (mobile device  server)/ Отговор на команда 0x11                                                                                                                            |
| 0x22  | Shell Version                       | Response of cmd 0x12 – with shell version (mobile device  server)/ Отговор на                                                                                                                                          |
| 0x23  | Crew Info                           | Response of cmd 0x13 – with crew info (See 1.3.13. CIRec) (mobile device  server)                                                                                                                                      |
| 0x24  | Vessel Info                         | Response to cmd 0x14 – with vessel info (See 1.3.) (mobile device  server)                                                                                                                                             |
| 0x25  | Full fishing trip report            | Response of cmd 0x15 (See 1.3.1. FTRec) (mobile device  server)                                                                                                                                                        |
| 0x26  | Current Fishing Day Information     | Response of cmd 0x16 (See 1.3.8. DIRec) (mobile device  server)                                                                                                                                                        |
| 0x27  |                                     | Not in use/ Не се използва                                                                                                                                                                                              |
| 0x28  |                                     | Not in use/ Не се използва                                                                                                                                                                                              |
| 0х29  |                                     | Not in use/ Не се използва                                                                                                                                                                                              |
| 0x2A  |                                     | Not in use/ Не се използва                                                                                                                                                                                              |
| …     |                                     |                                                                                                                                                                                                                         |
| 0x2E  | Configuration                       | Response current configuration values (mobile device  server)/ Отговор текущите стойности за конфигуриране                                                                                                             |
| 0x2F  | SetFishingTrip                      | Server side get fishing trip unique number from ISS and set it to device (server  mobile device). Response of Start Fishing Trip (0x30)/ Получаване на номер на риболовен рейс от ISS и изпращане към мобилния софтуер |
| 0x30  | Start Fishing Trip                  | Start fishing trip (mobile device  server)/ Начало на риболовен рейс                                                                                                                                                   |
| 0x31  | End Fishing Trip                    | End Fishing trip (mobile device  server)/ Край на риболовен рейс                                                                                                                                                       |
| 0x32  | PortLeave                           | Vessel port leave (mobile device  server) – not in use/ Излизане от пристанище – не се използва. Тези данни автоматично се изпращат от IDP782/ ST9100                                                                  |
| 0x33  | PortArrival                         | Vessel port arrival (mobile device  server) – not in use/ Влизане в пристанище – не се използва. Тези данни автоматично се изоращат от IDP782/ ST9100                                                                  |
| 0x34  | Start Fishing Operation             | Start fishing operation (mobile device  server)/ Начало на риболовна операция                                                                                                                                          |
| 0x35  | End Fishing Operation               | End fishing operation (mobile device  server)/ Край на риболовна операция                                                                                                                                              |
| 0x36  | 24DayReport                         | 24 day report (mobile device  server)/ Дневен отчет                                                                                                                                                                    |
| 0x37  | FO Data                             | Catch FO Data (mobile device  server)/ Данни за риболовната операция                                                                                                                                                   |
| …     |                                     |                                                                                                                                                                                                                         |
| 0x40  | LandingDeclaration                  | Landing Declaration (mobile device  server)/ Декларация за разтоварване                                                                                                                                                |
| 0x41  | Inspection Data                     | Inspection data (list of inspectors data) (mobile device  server)/ Данни за инсоектори, провели инспекция                                                                                                              |
| 0x42  | LostGear                            | LostGear declaration (mobile device  server)/ Изгубен уред                                                                                                                                                             |
| …     |                                     |                                                                                                                                                                                                                         |
| 0x4D  |                                     |                                                                                                                                                                                                                         |
| 0x4E  |                                     |                                                                                                                                                                                                                         |
| 0x4F  |                                     |                                                                                                                                                                                                                         |
| …     |                                     |                                                                                                                                                                                                                         |
| 0x50  | Get Meteo Data                      | Get meteo data from server (mobile device  server)/ Изискване на метео данни                                                                                                                                           |
| 0x51  | Meteo Data                          | Response of cmd 0x50 (See ) (server  mobile device)/ Отговор с метео данни                                                                                                                                             |
| 0x52  | UpdateCertificates                  | Cmd to server to update certs (mobile device  server)/ Искане за актуални данни по удостоверения, разрешителни и уреди за риболов                                                                                      |
| 0x53  | UpdateResponse                      | Update Permits, Certificates, ELBooks (server  mobile device)/ Отговор с данни за разрешителни, удостоверения и риболовни уреди за риболов                                                                             |
| 0x54  | ELBookPageResponse                  | Return to mobile on 0x30 SFT packet received (server  mobile device)/ Отговор на SFT с данни за риболовни дневници                                                                                                     |
| …     |                                     |                                                                                                                                                                                                                         |
| 0x60  | Get Device Universal Time           | Get Data Terminal Universal time (mobile device  server)                                                                                                                                                               |
| 0x61  | Device Universal time               | Current universal time                                                                                                                                                                                                  |
| 0x62  | Set Device Universal Time           | Set Data Terminal Universal time (server  mobile device) – Response 0x61                                                                                                                                               |
| …     |                                     |                                                                                                                                                                                                                         |
| 0x71  | Unknown Frame Type                  | Unknown frame type/ Непознат тип на фрейма                                                                                                                                                                              |
| 0x72  | Invalid Parameter                   | Invalid Parameters/ Грешни параметри                                                                                                                                                                                    |
| 0x73  | IDPPollCmd                          | Send poll cmd to IDP (server  mobile device)/ Изпращане на команда за Poll към IDP                                                                                                                                     |
| 0x74  | ELBPollCmd                          | Send poll cmd to FVMS-M Sw (server  mobile device)/ Изпращане на команда за Poll към ELB/FVMS/Друг аналогичен софтуер                                                                                                  |
| 0x75  | CRC Error                           | Failed CRC Check/ Грешна контролна сума                                                                                                                                                                                 |
| 0x76  | Ignore                              | Ignored packet (unknown ModuleID)/ Пакетът е получен и игнориран (*при непознат ModuleID*)                                                                                                                              |
| …     |                                     |                                                                                                                                                                                                                         |
| 0x7F  | Error                               | Undefined Error/ Неопределена грешка                                                                                                                                                                                    |
| 0x80  | GetINFullData                       | Get full IN data (ASPS, OECB, SSCB)/ Команда изискване на пълен набор IN данни                                                                                                                                          |
| 0x81  | FullASPSData                        | Response of 0x80 – FullASPSData, including ASPS, SSCB, OECB                                                                                                                                                             |
| 0x82  | GetINASPSData                       |                                                                                                                                                                                                                         |
| 0x83  | ASPSData                            | Response of 0x82 – ASPSData only                                                                                                                                                                                        |
| 0x84  | GetINSSCBData                       |                                                                                                                                                                                                                         |
| 0x85  | SSCBData                            | Response of 0x84 – SSCBData only                                                                                                                                                                                        |
| 0x86  | GetINOECBData                       |                                                                                                                                                                                                                         |
| 0x87  | OECBData                            | Response of 0x86 – OECBData only                                                                                                                                                                                        |
| …     |                                     |                                                                                                                                                                                                                         |
| 0xF0  |                                     |                                                                                                                                                                                                                         |
| 0xF1  |                                     |                                                                                                                                                                                                                         |
| 0xF2  |                                     |                                                                                                                                                                                                                         |
| 0xFF  |                                     |                                                                                                                                                                                                                         |

1.  1.  **Records description**

    1.  **Fishing trip (FTRec\\ Ftrip) – checked 2.1**

<table>
<tbody>
<tr class="odd">
<td><strong>Field</strong></td>
<td><strong>Type</strong></td>
<td><strong>Bytes</strong></td>
<td><strong>Note</strong></td>
</tr>
<tr class="even">
<td>Content</td>
<td>Byte</td>
<td>1</td>
<td><p>Bit 5 – Status </p>
<p>Bit 4 – Is Trip number real</p>
<p>Bit 2 – Data correction</p>
<p>Bit 1 </p></td>
</tr>
<tr class="odd">
<td>Trip number</td>
<td>Char[]</td>
<td>10</td>
<td><p>Information system trip number</p></td>
</tr>
<tr class="even">
<td>SFTRec</td>
<td>SFTRec</td>
<td>7~8</td>
<td>Start fishing trip data</td>
</tr>
<tr class="odd">
<td>EFTRec</td>
<td>EFTRec</td>
<td>9</td>
<td>End fishing trip data</td>
</tr>
<tr class="even">
<td>DIRecCount</td>
<td>Byte</td>
<td>1</td>
<td>Count of DIRecs records</td>
</tr>
<tr class="odd">
<td>DIRecs</td>
<td>DIRec[]</td>
<td>-</td>
<td>List of fishing days information – default minimal record DIRec</td>
</tr>
<tr class="even">
<td>CreationDTL</td>
<td>Uint32</td>
<td>4</td>
<td>Timestamp since 2018.01.01 00:00:00 UTC in seconds</td>
</tr>
<tr class="odd">
<td>DiscardedRecCrDTL</td>
<td>Uint32</td>
<td>4</td>
<td>Timestamp since 2018.01.01 00:00:00 UTC in seconds</td>
</tr>
<tr class="even">
<td>IsInsp</td>
<td>Byte</td>
<td>1</td>
<td>Is inspection data for current trip</td>
</tr>
<tr class="odd">
<td>Sens</td>
<td>SensRec</td>
<td>9</td>
<td>IDP Sensors data</td>
</tr>
<tr class="even">
<td>Packet length</td>
<td></td>
<td><span id="anchor"></span></td>
<td></td>
</tr>
</tbody>
</table>

>  **Start fishing trip record (SFTRec) – checked 2.1**

<table>
<tbody>
<tr class="odd">
<td><strong>Field</strong></td>
<td><strong>Type</strong></td>
<td><strong>Bytes</strong></td>
<td><strong>Note</strong></td>
</tr>
<tr class="even">
<td>Content</td>
<td>Byte</td>
<td>1</td>
<td><p>Bit 7 – Economic zone</p>
<p>Bit 6 – Statistical zone</p>
<p>Bit 5 – Status generation of record</p>
<p>Bit 4 – Position GPS part</p>
<p>Bit 3 – Port ID</p>
<p>Bit 2 – Data correction</p>
<p>Bit 1 </p></td>
</tr>
<tr class="odd">
<td>Timestamp</td>
<td>Uint32</td>
<td>4</td>
<td>Timestamp since 2018.01.01 00:00:00 UTC in seconds</td>
</tr>
<tr class="even">
<td>DiscardedRecCrDT</td>
<td>Uint32</td>
<td>4</td>
<td>Timestamp since 2018.01.01 00:00:00 UTC in seconds</td>
</tr>
<tr class="odd">
<td>Port Id</td>
<td>Uint16</td>
<td>2</td>
<td>Port of departure, (0 – 65535)</td>
</tr>
<tr class="even">
<td>FGRecsCount</td>
<td>Byte</td>
<td>1</td>
<td>Fishing Gear count – if 0 – no fg and data next byte is activity
(23.06.2019)</td>
</tr>
<tr class="odd">
<td>FGRecs</td>
<td>FGRec[]</td>
<td>14 * N</td>
<td>List of fishing gears used on vessel</td>
</tr>
<tr class="even">
<td>Activity</td>
<td>Byte</td>
<td>1</td>
<td>Sequence number from list activities (0-255)</td>
</tr>
<tr class="odd">
<td>Economic zone</td>
<td><p>Uint16</p></td>
<td>2</td>
<td><p>Sequence number from list economic zones (0-65535)</p>
<p>. Can be omit the and the calculation to be performed on the server
based on geographic coordinates.</p>
<p>2 bytes – for 65535 zones?</p></td>
</tr>
<tr class="even">
<td>Statistical zone</td>
<td>Uint16</td>
<td>2</td>
<td><p>Sequence number from list statistical or other zones
(0-65535)</p>
<p>. Can be omit the and the calculation to be performed on the server
based on geographic coordinates.</p>
<p>2 bytes – for 65535 zones?</p></td>
</tr>
<tr class="odd">
<td>Position</td>
<td>PosRec</td>
<td>18</td>
<td>GPS position and time</td>
</tr>
<tr class="even">
<td>tripnumber</td>
<td></td>
<td></td>
<td></td>
</tr>
<tr class="odd">
<td>Sens</td>
<td>SensRec</td>
<td>9</td>
<td>IDP Sensors data</td>
</tr>
<tr class="even">
<td>Packet Length</td>
<td>26 + (14 * N) bytes</td>
<td>65 bytes after IDP Scortel Packet<span id="anchor-1"></span></td>
<td></td>
</tr>
</tbody>
</table>

>  **End fishing trip record (EFTRec) – checked 2.1**

<table>
<tbody>
<tr class="odd">
<td><strong>Field</strong></td>
<td><strong>Type</strong></td>
<td><strong>Bytes</strong></td>
<td><strong>Note</strong></td>
</tr>
<tr class="even">
<td>Context</td>
<td>Byte</td>
<td>1</td>
<td><p>Bit 7 – Estimated time of arrival</p>
<p>Bit 6 – Port of arrival Id</p>
<p>Bit 5 – End of fishing activities timestamp</p>
<p>Bit 4 – Position GPS part</p>
<p>Bit 3 – Status generation of record</p>
<p>Bit 2 – Data correction</p>
<p>Bit 1 </p></td>
</tr>
<tr class="odd">
<td>Estimated Date and time</td>
<td>Uint32</td>
<td>4</td>
<td>Estimated time of arrival – Timestamp since 2018.01.01 00:00:00 UTC
in seconds</td>
</tr>
<tr class="even">
<td>Port of arrival Id</td>
<td>Uint16</td>
<td>2</td>
<td>Port of arrival (0 – 65535)</td>
</tr>
<tr class="odd">
<td>Date and time – end of fishing activities</td>
<td>Byte</td>
<td>4</td>
<td>End of fishing activities – Timestamp since 2018.01.01 00:00:00 UTC
in seconds</td>
</tr>
<tr class="even">
<td>Position</td>
<td>PosRec</td>
<td>18</td>
<td>GPS Position and time</td>
</tr>
<tr class="odd">
<td>Trip number</td>
<td>Char[]</td>
<td>23</td>
<td><p>Byte 0 – size of the following data (22 bytes) – Information
system trip number</p></td>
</tr>
<tr class="even">
<td>CreationDT</td>
<td>Uint32</td>
<td>4</td>
<td>Timestamp since 2018.01.01 00:00:00 UTC in seconds</td>
</tr>
<tr class="odd">
<td>DiscardedRecCrDT</td>
<td>Uint32</td>
<td>4</td>
<td>Timestamp since 2018.01.01 00:00:00 UTC in seconds</td>
</tr>
<tr class="even">
<td>Sens</td>
<td>SensRec</td>
<td>9</td>
<td>IDP Sensors data</td>
</tr>
<tr class="odd">
<td>Packet length</td>
<td>5 ~ 37 bytes</td>
<td><span id="anchor-2"></span></td>
<td></td>
</tr>
</tbody>
</table>

>  **Fishing operation record (FORec) – checked 2.1**

<table>
<tbody>
<tr class="odd">
<td><strong>Field</strong></td>
<td><strong>Type</strong></td>
<td><strong>Bytes</strong></td>
<td><strong>Note</strong></td>
</tr>
<tr class="even">
<td>Content</td>
<td>Byte</td>
<td>1</td>
<td><p>Bit 7</p>
<p>Bit 6 – 25.06.2019 v.1.0.0.8 -&gt;</p>
<p>Bit 5 </p>
<p>Bit 3 – Structure creation – not implemented</p>
<p>Bit 2 – Data correction</p>
<p>Bit 1 </p></td>
</tr>
<tr class="odd">
<td>StartFO</td>
<td>SEFORec</td>
<td>13~15</td>
<td><p>Start of fishing operation record (content bit 3 = 0)</p></td>
</tr>
<tr class="even">
<td>EndFO</td>
<td>SEFORec</td>
<td>12~14</td>
<td>End of fishing operation record (content bit 3 = 1)</td>
</tr>
<tr class="odd">
<td>JFRec</td>
<td>JFRec</td>
<td>~</td>
<td>Joint vessels information</td>
</tr>
<tr class="even">
<td>FCRecsCount</td>
<td>Byte</td>
<td>1</td>
<td>Count of FCRecs records</td>
</tr>
<tr class="odd">
<td>FCRecs</td>
<td>FCRec[]</td>
<td>13~18*N</td>
<td>List of fishing catch</td>
</tr>
<tr class="even">
<td>CreationDT</td>
<td>Uint32</td>
<td>4</td>
<td>Timestamp since 2018.01.01 00:00:00 UTC in seconds</td>
</tr>
<tr class="odd">
<td>DiscardedRecCrDT</td>
<td>Uint32</td>
<td>4</td>
<td>Timestamp since 2018.01.01 00:00:00 UTC in seconds</td>
</tr>
<tr class="even">
<td>Tripnumber</td>
<td>Byte[]</td>
<td>~</td>
<td>Tripnumber</td>
</tr>
<tr class="odd">
<td>Sens</td>
<td>SensRec</td>
<td>9</td>
<td>IDP Sensors data</td>
</tr>
<tr class="even">
<td>Packet length</td>
<td></td>
<td><span id="anchor-3"></span></td>
<td></td>
</tr>
</tbody>
</table>

>  **Start/ end of fishing operation record (SEFORec) – checked
            2.1**

<table>
<tbody>
<tr class="odd">
<td><strong>Field</strong></td>
<td><strong>Type</strong></td>
<td><strong>Bytes</strong></td>
<td><strong>Note</strong></td>
</tr>
<tr class="even">
<td>Content</td>
<td>Byte</td>
<td>1</td>
<td><p>Bit 7</p>
<p>Bit 6</p>
<p>Bit 5 – Status generation of record</p>
<p>Bit 4 – Position GPS part</p>
<p>Bit 3 – Start end FO</p>
<p>Bit 2 – Data Correction</p>
<p>Bit 1 </p></td>
</tr>
<tr class="odd">
<td>FGRecsCount</td>
<td>Byte</td>
<td>1</td>
<td>Count of FGRecs records</td>
</tr>
<tr class="even">
<td>FGRecs</td>
<td>FGRec[]</td>
<td>~</td>
<td>List of Fishing gear records</td>
</tr>
<tr class="odd">
<td>Economic zone</td>
<td>Uint16</td>
<td>2</td>
<td><p>Sequence number from list economic zones (0-255)</p>
<p>. Can be omit the and the calculation to be performed on the server
based on geographic coordinates.</p></td>
</tr>
<tr class="even">
<td>Statistical or other zone</td>
<td>Uint16</td>
<td>2</td>
<td><p>Sequence number from list statistical or other zones (0-255)</p>
<p>. Can be omit the and the calculation to be performed on the server
based on geographic coordinates.</p></td>
</tr>
<tr class="odd">
<td>Position</td>
<td>PosRec</td>
<td>18</td>
<td>GPS position – there is a Timestamp field witch is used for time
checks </td>
</tr>
<tr class="even">
<td>FODepth</td>
<td>Byte</td>
<td>1</td>
<td>Depth in meters * 10 of start and end of FO</td>
</tr>
<tr class="odd">
<td>CreationDT</td>
<td>Uint32</td>
<td>4</td>
<td>Timestamp since 2018.01.01 00:00:00 UTC in seconds</td>
</tr>
<tr class="even">
<td>DiscardedRecCrDT</td>
<td>Uint32</td>
<td>4</td>
<td>Timestamp since 2018.01.01 00:00:00 UTC in seconds</td>
</tr>
<tr class="odd">
<td>Sens</td>
<td>SensRec</td>
<td>9</td>
<td>IDP Sensors data</td>
</tr>
<tr class="even">
<td>Pos</td>
<td>PosRec</td>
<td></td>
<td>Position</td>
</tr>
<tr class="odd">
<td>Packet length</td>
<td></td>
<td><span id="anchor-4"></span></td>
<td></td>
</tr>
</tbody>
</table>

>  **Fishing Catches Record (FCRec) – checked 2.1**

<table>
<tbody>
<tr class="odd">
<td><strong>Field</strong></td>
<td><strong>Type</strong></td>
<td><strong>Bytes</strong></td>
<td><strong>Note</strong></td>
<td><strong>Version FVMS-M</strong></td>
</tr>
<tr class="even">
<td><strong>Content</strong></td>
<td><strong>Byte</strong></td>
<td><strong>1</strong></td>
<td><p>Bit 7 – add at ld (FVMS v. 3.0.0.8)</p>
<p>Bit 6 – priulov (FVMS v. 3.0.0.8)</p>
<p>Bit 5 – BMS or LSC (FVMS v. 3.0.0.8)</p>
<p>Bit 4 – rec is deleted at client side</p>
<p>Bit 3 – Discarded Data</p>
<p>Bit 2 – Data Correction</p>
<p>Bit 1 </p></td>
<td><p>bit 5, 6, 7 - 04.2021. – 3.0.0.8</p>
<p>Bit 4 – 05.2021 – 3.0.0.8</p></td>
</tr>
<tr class="odd">
<td>Srec</td>
<td>Srec</td>
<td>2~7</td>
<td><p>Species description record </p></td>
<td></td>
</tr>
<tr class="even">
<td>Presentation</td>
<td>Byte</td>
<td>1</td>
<td>Sequence number from list presentations (0-255)</td>
<td></td>
</tr>
<tr class="odd">
<td>Condition</td>
<td>Byte</td>
<td>1</td>
<td>Sequence number from list conditions (0-255)</td>
<td></td>
</tr>
<tr class="even">
<td>QuantityType</td>
<td>Byte</td>
<td>1</td>
<td>Sequence number from list quantitytype (0-255)</td>
<td></td>
</tr>
<tr class="odd">
<td>Discarded</td>
<td>Uint32</td>
<td>4</td>
<td>Weight or units</td>
<td></td>
</tr>
<tr class="even">
<td>DiscardedCount</td>
<td>Uint16</td>
<td>2</td>
<td>Count of units</td>
<td></td>
</tr>
<tr class="odd">
<td>DiscardedType</td>
<td>Byte</td>
<td>1</td>
<td>Sequence number from list presentations (0-255)</td>
<td></td>
</tr>
<tr class="even">
<td>Weight</td>
<td>Uint32</td>
<td>4</td>
<td>Weight in kilograms</td>
<td></td>
</tr>
<tr class="odd">
<td>WeightCount</td>
<td>Uint16</td>
<td>2</td>
<td>Count of units</td>
<td></td>
</tr>
<tr class="even">
<td>CreationDT</td>
<td>Uint32</td>
<td>4</td>
<td>Timestamp since 2018.01.01 00:00:00 UTC in seconds</td>
<td></td>
</tr>
<tr class="odd">
<td>DiscardedRecCrDT</td>
<td>Uint32</td>
<td>4</td>
<td>Timestamp since 2018.01.01 00:00:00 UTC in seconds</td>
<td></td>
</tr>
<tr class="even">
<td>SizeGroup</td>
<td>Byte</td>
<td>1 </td>
<td>Size group of specific species</td>
<td></td>
</tr>
<tr class="odd">
<td>QTCount</td>
<td>UInt16</td>
<td>2</td>
<td>QyantityType count of storage containers</td>
<td>Planned for 3.0.0.8</td>
</tr>
<tr class="even">
<td>QTTara</td>
<td>UInt16</td>
<td>2</td>
<td>Weight “Tara” of storage container</td>
<td>Planned for 3.0.0.8 not implemented</td>
</tr>
<tr class="odd">
<td>QTBruto</td>
<td>UInt16</td>
<td>2</td>
<td>Weight “Bruto” of storage container</td>
<td>Planned for 3.0.0.8 not implemented</td>
</tr>
<tr class="even">
<td>Packet length</td>
<td>18 ~ 23 bytes</td>
<td></td>
<td><span id="anchor-5"></span></td>
<td></td>
</tr>
</tbody>
</table>

** **

>  **Day information report record (DIRec) – checked 2.1**

<table>
<tbody>
<tr class="odd">
<td><strong>Field</strong></td>
<td><strong>Type</strong></td>
<td><strong>Bytes</strong></td>
<td><strong>Note</strong></td>
</tr>
<tr class="even">
<td>Content</td>
<td>Bite</td>
<td>1</td>
<td><p>Bit 7</p>
<p>Bit 6 </p>
<p>Bit 5</p>
<p>Bit 4 – Status </p>
<p>Bit 3 – Is trip number real</p>
<p>Bit 2 – Data Correction</p>
<p>Bit 1 </p></td>
</tr>
<tr class="odd">
<td>Trip number</td>
<td>Char[]</td>
<td>23</td>
<td><p>Byte 0 – size of the following data (22 bytes) – Information
system trip number</p></td>
</tr>
<tr class="even">
<td>CreationDT</td>
<td>Uint32</td>
<td>4</td>
<td>Timestamp since 2018.01.01 00:00:00 UTC in seconds</td>
</tr>
<tr class="odd">
<td>DiscardedRecCrDT</td>
<td>Uint32</td>
<td>4</td>
<td>Timestamp since 2018.01.01 00:00:00 UTC in seconds</td>
</tr>
<tr class="even">
<td>TransRecCount</td>
<td>Byte</td>
<td>1</td>
<td>Count of TransRec records</td>
</tr>
<tr class="odd">
<td>TransRec</td>
<td>TransRec[]</td>
<td>~</td>
<td>List of transbording operations</td>
</tr>
<tr class="even">
<td>InsRecCount</td>
<td>Byte</td>
<td>1</td>
<td>Count of InsRec records</td>
</tr>
<tr class="odd">
<td>InsRecs</td>
<td>InsRec[]</td>
<td>45</td>
<td>Inspection record</td>
</tr>
<tr class="even">
<td>FOCount</td>
<td>Byte</td>
<td>1</td>
<td>Count of FO records</td>
</tr>
<tr class="odd">
<td>Fos</td>
<td>FORec[]</td>
<td>~</td>
<td>List of fishing operations and catch</td>
</tr>
<tr class="even">
<td>Position</td>
<td>PosRec</td>
<td>18</td>
<td>GPS position and time</td>
</tr>
<tr class="odd">
<td>Sens</td>
<td>SensRec</td>
<td>9</td>
<td>IDP Sensors data</td>
</tr>
<tr class="even">
<td>Packet length</td>
<td>~</td>
<td><span id="anchor-6"></span></td>
<td></td>
</tr>
</tbody>
</table>

** **

>  **Trans boarding record (TransRec) – checked 2.1**

<table>
<tbody>
<tr class="odd">
<td><strong>Field</strong></td>
<td><strong>Type</strong></td>
<td><strong>Bytes</strong></td>
<td><strong>Note</strong></td>
</tr>
<tr class="even">
<td>Content</td>
<td>Byte</td>
<td>1</td>
<td><p>Bit 7 Economic zone</p>
<p>Bit 6 Statistical zone</p>
<p>Bit 5 Vessel role</p>
<p>Bit 4 – Position GPS part</p>
<p>Bit 2 – Data correction</p>
<p>Bit 1 </p></td>
</tr>
<tr class="odd">
<td>Economic zone</td>
<td>Uint16</td>
<td>2</td>
<td><p>Sequence number from list economic zones (0-65535)</p>
<p>. Can be omit the and the calculation to be performed on the server
based on geographic coordinates.</p></td>
</tr>
<tr class="even">
<td>Statistical zone</td>
<td>Uint16</td>
<td>2</td>
<td><p>Sequence number from list statistical or other zones
(0-65535)</p>
<p>. Can be omit the and the calculation to be performed on the server
based on geographic coordinates.</p></td>
</tr>
<tr class="odd">
<td>FCRecsCount</td>
<td>Byte</td>
<td>1</td>
<td>Count of FCRecs records</td>
</tr>
<tr class="even">
<td>FCRecs</td>
<td>FCRec[]</td>
<td>13~18*N</td>
<td>List of fishing catch</td>
</tr>
<tr class="odd">
<td>Position</td>
<td>PosRec</td>
<td>18</td>
<td>GPS position</td>
</tr>
<tr class="even">
<td>CreationDT</td>
<td>Uint32</td>
<td>4</td>
<td>Timestamp since 2018.01.01 00:00:00 UTC in seconds</td>
</tr>
<tr class="odd">
<td>DiscardedRecCrDT</td>
<td>Uint32</td>
<td>4</td>
<td>Timestamp since 2018.01.01 00:00:00 UTC in seconds</td>
</tr>
<tr class="even">
<td>VrecsCount</td>
<td>Byte</td>
<td>1</td>
<td>Count of Vrecs records if 0 – no vrec data</td>
</tr>
<tr class="odd">
<td>Vrecs</td>
<td>Vrec[]</td>
<td>~</td>
<td>List of Joint Vessel information</td>
</tr>
<tr class="even">
<td>Packet length</td>
<td>~</td>
<td><span id="anchor-7"></span></td>
<td></td>
</tr>
</tbody>
</table>

>  **Joint fishing record (JFRec) – checked 2.1**

<table>
<tbody>
<tr class="odd">
<td><strong>Field</strong></td>
<td><strong>Type</strong></td>
<td><strong>Bytes</strong></td>
<td><strong>Note</strong></td>
</tr>
<tr class="even">
<td>Content</td>
<td>Byte</td>
<td>1</td>
<td><p>Bit 2 – Data correction</p>
<p>Bit 1 </p></td>
</tr>
<tr class="odd">
<td>VrecsCount</td>
<td>Byte</td>
<td>1</td>
<td>Count of Vrecs records</td>
</tr>
<tr class="even">
<td>Vrecs</td>
<td>Vrec[]</td>
<td>~</td>
<td>List of Joint Vessel information</td>
</tr>
<tr class="odd">
<td>CreationDT</td>
<td>Uint32</td>
<td>4</td>
<td>Timestamp since 2018.01.01 00:00:00 UTC in seconds</td>
</tr>
<tr class="even">
<td>DiscardedRecCrDT</td>
<td>Uint32</td>
<td>4</td>
<td>Timestamp since 2018.01.01 00:00:00 UTC in seconds</td>
</tr>
<tr class="odd">
<td>Position</td>
<td>PosRec</td>
<td>18</td>
<td>GPS position</td>
</tr>
<tr class="even">
<td>Packet length</td>
<td></td>
<td><span id="anchor-8"></span></td>
<td></td>
</tr>
</tbody>
</table>

** **

>  **Inspection record (InsRec) – checked 2.1**

<table>
<tbody>
<tr class="odd">
<td><strong>Field</strong></td>
<td><strong>Type</strong></td>
<td><strong>Bytes</strong></td>
<td><strong>Note</strong></td>
</tr>
<tr class="even">
<td>Content</td>
<td>Byte</td>
<td>1</td>
<td><p>Bit 3 – Is Trip number</p>
<p>Bit 2 – Data correction</p>
<p>Bit 1 </p></td>
</tr>
<tr class="odd">
<td>Country</td>
<td>Byte</td>
<td>1</td>
<td>Country 3 symbols abbreviation from list (0-255) </td>
</tr>
<tr class="even">
<td>InspIdType</td>
<td>Byte</td>
<td>1</td>
<td>Inspector identification type</td>
</tr>
<tr class="odd">
<td>InspData</td>
<td>Byte[]</td>
<td></td>
<td><p>Inspector name or other identification number or name.</p>
<p> First byte is length of the field, if length = 0 there is no data.
</p>
<p>The data is coded in Unicode.</p></td>
</tr>
<tr class="even">
<td>Position</td>
<td>PosRec</td>
<td>18</td>
<td>GPS position + time</td>
</tr>
<tr class="odd">
<td>CreationDT</td>
<td>Uint32</td>
<td>4</td>
<td>Timestamp since 2018.01.01 00:00:00 UTC in seconds</td>
</tr>
<tr class="even">
<td>DiscardedRecCrDT</td>
<td>Uint32</td>
<td>4</td>
<td>Timestamp since 2018.01.01 00:00:00 UTC in seconds</td>
</tr>
<tr class="odd">
<td>Trip number</td>
<td>Char[]</td>
<td>21</td>
<td><p>Information system trip number</p></td>
</tr>
<tr class="even">
<td>Packet length</td>
<td>42 + N bytes</td>
<td><span id="anchor-9"></span></td>
<td></td>
</tr>
</tbody>
</table>

>  **InsDataRec **

<table>
<tbody>
<tr class="odd">
<td><strong>Field</strong></td>
<td><strong>Type</strong></td>
<td><strong>Bytes</strong></td>
<td><strong>Note</strong></td>
</tr>
<tr class="even">
<td>Content</td>
<td>Byte</td>
<td>1</td>
<td><p>Bit 3 – Is Trip number</p></td>
</tr>
<tr class="odd">
<td>Trip number</td>
<td>Char[]</td>
<td>21</td>
<td><p>Information system trip number</p></td>
</tr>
<tr class="even">
<td>Count</td>
<td>Byte</td>
<td>1</td>
<td>Number of InsRec records</td>
</tr>
<tr class="odd">
<td>InsRecs</td>
<td>InsRec[]</td>
<td>InsRecLen</td>
<td>InsRec List</td>
</tr>
<tr class="even">
<td>Sens</td>
<td>SensRec</td>
<td>9</td>
<td>IDP Sensors data</td>
</tr>
<tr class="odd">
<td>Packet length</td>
<td>23 + InsRecLen bytes</td>
<td></td>
<td></td>
</tr>
</tbody>
</table>

>  **Vessel record (Vrec) – checked 2.1**

<table>
<tbody>
<tr class="odd">
<td><strong>Field</strong></td>
<td><strong>Type</strong></td>
<td><strong>Bytes</strong></td>
<td><strong>Note</strong></td>
</tr>
<tr class="even">
<td>Content1</td>
<td>Byte</td>
<td>1</td>
<td><p>Bit 7</p>
<p>Bit 6</p>
<p>Bit 5</p>
<p>Bit 4</p>
<p>Bit 3</p>
<p>Bit 2</p>
<p>Bit 1</p>
<p>Bit 0 - Home port</p></td>
</tr>
<tr class="odd">
<td>Content2</td>
<td>Byte</td>
<td>1</td>
<td><p>Bit 7</p>
<p>Bit 6</p>
<p>Bit 5</p>
<p>Bit 4</p>
<p>Bit 3</p>
<p>Bit 2</p>
<p>Bit 1</p>
<p>Bit 0</p></td>
</tr>
<tr class="even">
<td>Content3</td>
<td>Byte</td>
<td>1</td>
<td><p>Bit 7</p>
<p>Bit 6</p>
<p>Bit 3</p>
<p>Bit 2 – Data correction</p>
<p>Bit 1 </p></td>
</tr>
<tr class="odd">
<td>Vessel name</td>
<td>Byte[]</td>
<td>1+N</td>
<td>Наименование на другия участник в претоварването на улов, First byte
is length of the field, N – data</td>
</tr>
<tr class="even">
<td>Description</td>
<td>Byte[]</td>
<td>1+N</td>
<td>Other vessel description, First byte is the length of data</td>
</tr>
<tr class="odd">
<td>Country</td>
<td>byte</td>
<td>1</td>
<td>Country from list (0-255)</td>
</tr>
<tr class="even">
<td>External mark</td>
<td>Byte[]</td>
<td>1+N</td>
<td>Exter mark, First byte is the length of data</td>
</tr>
<tr class="odd">
<td>MMSI</td>
<td>Byte[]</td>
<td>1+N</td>
<td>Необходим при използване на AIS и радио лиценз; Класификация на
плавателните съдове. Ще се изисква задължително от риболовците.</td>
</tr>
<tr class="even">
<td>CFR</td>
<td>Byte[]</td>
<td>1+N</td>
<td>Vessel CFR, first byte is the length of data</td>
</tr>
<tr class="odd">
<td>Radio Call sign</td>
<td>Byte[]</td>
<td>1+N</td>
<td>Sequence number from list vessels</td>
</tr>
<tr class="even">
<td>License</td>
<td>Byte[]</td>
<td>1+N</td>
<td>Vessel License</td>
</tr>
<tr class="odd">
<td>RadioLicense</td>
<td>Byte[]</td>
<td>1+N</td>
<td>Vessel Radio license</td>
</tr>
<tr class="even">
<td>RSS Nr.</td>
<td>Byte[]</td>
<td>1+N</td>
<td>При риболовстване Великобритания/ Норвегия</td>
</tr>
<tr class="odd">
<td>Home port </td>
<td>Uint16</td>
<td>2</td>
<td>Home port – sequence number from list ports (0-65553)</td>
</tr>
<tr class="even">
<td>RegPort</td>
<td>Uint16</td>
<td>2</td>
<td>Port of registrationi – sequence number from list (0-65553)</td>
</tr>
<tr class="odd">
<td>VesselLen</td>
<td>Float</td>
<td>4</td>
<td>Vessel length</td>
</tr>
<tr class="even">
<td>VesselWidth</td>
<td>Float</td>
<td>4</td>
<td>Vessel width</td>
</tr>
<tr class="odd">
<td>VesselDraft</td>
<td>Float</td>
<td>4</td>
<td>Vessel draft</td>
</tr>
<tr class="even">
<td>VesselGrossTonage</td>
<td>Float</td>
<td>4</td>
<td>Vessel gross tonage</td>
</tr>
<tr class="odd">
<td>VesselOwner</td>
<td>Byte[]</td>
<td>1+N</td>
<td>Vessel Owner, First byte is the lenfth of data</td>
</tr>
<tr class="even">
<td>VesselCompanyAddr_EIK</td>
<td>Byte[] </td>
<td>1+N</td>
<td>Vessel company address or eik, first byte is the length of data</td>
</tr>
<tr class="odd">
<td>CreationDT</td>
<td>Uint32</td>
<td>4</td>
<td>Timestamp since 2018.01.01 00:00:00 UTC in seconds</td>
</tr>
<tr class="even">
<td>DiscardedRecCrDT</td>
<td>Uint32</td>
<td>4</td>
<td>Timestamp since 2018.01.01 00:00:00 UTC in seconds</td>
</tr>
<tr class="odd">
<td>Udo</td>
<td>1+N</td>
<td>~</td>
<td>Fishing license</td>
</tr>
<tr class="even">
<td>Record length</td>
<td>12 ~ 182 bytes</td>
<td><span id="anchor-10"></span></td>
<td></td>
</tr>
</tbody>
</table>

>  **Crew information record (CIRec)**

|               |                      |           |                      |
|---------------|----------------------|-----------|----------------------|
| **Field**     | **Type**             | **Bytes** | **Note**             |
| CMRecs        | CMRec\[\]            | ~         | List of crew members |
| Packet length | 45\*N ~ 397\*N bytes |           |                      |

>   **Crew member record (CMRec) – checked 2.1direc**
2.

<table>
<tbody>
<tr class="odd">
<td><strong>Field</strong></td>
<td><strong>Type</strong></td>
<td><strong>Bytes</strong></td>
<td><strong>Note</strong></td>
</tr>
<tr class="even">
<td>Content</td>
<td>Byte</td>
<td>1</td>
<td><p>Bit 7</p>
<p>Bit 6</p>
<p> • 0 – excluded zipcode</p>
<p> • 1 – included zipcode </p>
<p>Bit 5</p>
<p> • 0 – excluded username and password</p>
<p> • 1 – included username and password</p>
<p>Bit 4</p>
<p> • 0 – excluded fax</p>
<p> • 1 – included fax</p>
<p>Bit 3</p>
<p> • 0 – excluded email</p>
<p> • 1 – included email</p>
<p>Bit 2</p>
<p> • 0 – excluded nationality</p>
<p> • 1 – included nationality</p>
<p>Bit 1</p>
<p> • 0 – excluded medical info</p>
<p> • 1 – included medical info</p>
<p>Bit 0</p>
<p> • 0 – excluded notes</p>
<p> • 1 – included notes</p></td>
</tr>
<tr class="odd">
<td>Content_2</td>
<td>Byte</td>
<td>1</td>
<td><p>Bit 2 – Data correction</p>
<p>Bit 1 </p></td>
</tr>
<tr class="even">
<td>Name</td>
<td>Byte[]</td>
<td>1+N</td>
<td>Name of crew member, First byte is the length of data</td>
</tr>
<tr class="odd">
<td>Address</td>
<td>Byte[]</td>
<td>1+N</td>
<td>Address, First byte is the length of data</td>
</tr>
<tr class="even">
<td>Zip code</td>
<td>Uint16</td>
<td>2</td>
<td>Zip code</td>
</tr>
<tr class="odd">
<td>Crew position</td>
<td>Byte[]</td>
<td>1+N</td>
<td>First byte is the length of data </td>
</tr>
<tr class="even">
<td>Username</td>
<td>Byte[] </td>
<td>1+N</td>
<td>Account access to onboard sw – user name, First byte is the length
of data</td>
</tr>
<tr class="odd">
<td>Password</td>
<td>Byte[] </td>
<td>1+N</td>
<td>Account access to onboard sw – user password, First byte is the
length of data</td>
</tr>
<tr class="even">
<td>CellPhone</td>
<td>Byte[]</td>
<td>1+N</td>
<td>Phone number, First byte is the length of data</td>
</tr>
<tr class="odd">
<td>Fax</td>
<td>Byte[]</td>
<td>1+N</td>
<td>Fax number, First byte is the length of data</td>
</tr>
<tr class="even">
<td>e-mail</td>
<td>Byte[]</td>
<td>1+N</td>
<td>e-mail</td>
</tr>
<tr class="odd">
<td>Nationality</td>
<td>Byte</td>
<td>1</td>
<td>Sequence number from list nationalities (0-255)</td>
</tr>
<tr class="even">
<td>Medical information</td>
<td>Byte[]</td>
<td>1+N</td>
<td>Medical information</td>
</tr>
<tr class="odd">
<td>Notes</td>
<td>Byte[]</td>
<td>1+N</td>
<td>Notes</td>
</tr>
<tr class="even">
<td>CreationDT</td>
<td>Uint32</td>
<td>4</td>
<td>Timestamp since 2018.01.01 00:00:00 UTC in seconds</td>
</tr>
<tr class="odd">
<td>DiscardedRecCrDT</td>
<td>Uint32</td>
<td>4</td>
<td>Timestamp since 2018.01.01 00:00:00 UTC in seconds</td>
</tr>
<tr class="even">
<td>Packet length</td>
<td>47~397 bytes</td>
<td><span id="anchor-11"></span></td>
<td></td>
</tr>
</tbody>
</table>

>  **Species record (SRec) – checked 2.1 **

<table>
<tbody>
<tr class="odd">
<td><strong>Field</strong></td>
<td><strong>Type</strong></td>
<td><strong>Bytes</strong></td>
<td><strong>Note</strong></td>
</tr>
<tr class="even">
<td>Content</td>
<td>Byte</td>
<td>1</td>
<td><p>Bit 7</p>
<p><del>Bit 6</del></p>
<p>Bit 2 – Data correction</p>
<p>Bit 1 </p></td>
</tr>
<tr class="odd">
<td>species</td>
<td>Unit16</td>
<td>2</td>
<td><p>Sequence number from list species (0-255)</p></td>
</tr>
<tr class="even">
<td>Price</td>
<td>Float</td>
<td>4</td>
<td>Price</td>
</tr>
<tr class="odd">
<td>Currency</td>
<td>Byte</td>
<td>1</td>
<td>Sequence number from list currencies (0-255)</td>
</tr>
<tr class="even">
<td>CreationDT</td>
<td>Uint32</td>
<td>4</td>
<td>Timestamp since 2018.01.01 00:00:00 UTC in seconds</td>
</tr>
<tr class="odd">
<td>CreationDT</td>
<td>Uint32</td>
<td>4</td>
<td>Timestamp since 2018.01.01 00:00:00 UTC in seconds</td>
</tr>
<tr class="even">
<td>Packet length</td>
<td>11 ~ 16 bytes</td>
<td><span id="anchor-12"></span></td>
<td></td>
</tr>
</tbody>
</table>

>  **Fishing gear record (FGRec) – checked 2.1**

<table>
<tbody>
<tr class="odd">
<td><strong>Field</strong></td>
<td><strong>Type</strong></td>
<td><strong>Bytes</strong></td>
<td><strong>Note</strong></td>
</tr>
<tr class="even">
<td>Content</td>
<td>Byte</td>
<td>1</td>
<td><p>Bit 7</p>
<p>Bit 6 </p>
<p>Bit 5</p>
<p>Bit 4 </p>
<p>Bit 2 – Data correction</p>
<p>Bit 1 </p></td>
</tr>
<tr class="odd">
<td>Fishing gear</td>
<td>Byte</td>
<td>1</td>
<td><p>Sequence number from list fishing gears (0-255)</p></td>
</tr>
<tr class="even">
<td>Gear eye</td>
<td>Uint16</td>
<td>2</td>
<td>Gear eye (0 – 65535)</td>
</tr>
<tr class="odd">
<td>Length</td>
<td>Uint16</td>
<td>2</td>
<td>Gear length (0 – 65535)</td>
</tr>
<tr class="even">
<td>Height</td>
<td>Uint16</td>
<td>2</td>
<td>Gear height (0 – 65535)</td>
</tr>
<tr class="odd">
<td>Trademark</td>
<td>Uint16</td>
<td>2</td>
<td>Trademark identifier (0 – 65535)</td>
</tr>
<tr class="even">
<td>Model</td>
<td>Uint16 </td>
<td>2</td>
<td>Fishing gear model identifier (0 – 65535)</td>
</tr>
<tr class="odd">
<td>Count</td>
<td>Uint16</td>
<td>2</td>
<td>Count – used for some of the fishing gear types (0 – 65535)</td>
</tr>
<tr class="even">
<td>CreationDT</td>
<td>Uint32</td>
<td>4</td>
<td>Timestamp since 2018.01.01 00:00:00 UTC in seconds</td>
</tr>
<tr class="odd">
<td>DiscardedRecCrDT</td>
<td>Uint32</td>
<td>4</td>
<td>Timestamp since 2018.01.01 00:00:00 UTC in seconds</td>
</tr>
<tr class="even">
<td>CertNumber</td>
<td></td>
<td>10???</td>
<td></td>
</tr>
<tr class="odd">
<td>Packet length</td>
<td>22 bytes</td>
<td><span id="anchor-13"></span></td>
<td></td>
</tr>
</tbody>
</table>

>  **Lost Gear (LFGRec)**

<table>
<tbody>
<tr class="odd">
<td><strong>Field</strong></td>
<td><strong>Type</strong></td>
<td><strong>Bytes</strong></td>
<td><strong>Note</strong></td>
</tr>
<tr class="even">
<td>Content</td>
<td>Byte</td>
<td>1</td>
<td></td>
</tr>
<tr class="odd">
<td>Fishing gear</td>
<td>Byte</td>
<td>1</td>
<td><p>Sequence number from list fishing gears (0-255)</p></td>
</tr>
<tr class="even">
<td>Gear eye</td>
<td>Uint16</td>
<td>2</td>
<td>Gear eye (0 – 65535)</td>
</tr>
<tr class="odd">
<td>Length</td>
<td>Uint16</td>
<td>2</td>
<td>Gear length (0 – 65535)</td>
</tr>
<tr class="even">
<td>Height</td>
<td>Uint16</td>
<td>2</td>
<td>Gear height (0 – 65535)</td>
</tr>
<tr class="odd">
<td>Trademark</td>
<td>Uint16</td>
<td>2</td>
<td>Trademark identifier (0 – 65535)</td>
</tr>
<tr class="even">
<td>Model</td>
<td>Uint16 </td>
<td>2</td>
<td>Fishing gear model identifier (0 – 65535)</td>
</tr>
<tr class="odd">
<td>Count</td>
<td>Uint16</td>
<td>2</td>
<td>Count – used for some of the fishing gear types (0 – 65535)</td>
</tr>
<tr class="even">
<td>CreationDT</td>
<td>Uint32</td>
<td>4</td>
<td>Timestamp since 2018.01.01 00:00:00 UTC in seconds</td>
</tr>
<tr class="odd">
<td>DiscardedRecCrDT</td>
<td>Uint32</td>
<td>4</td>
<td>Timestamp since 2018.01.01 00:00:00 UTC in seconds</td>
</tr>
<tr class="even">
<td>CertNumber</td>
<td></td>
<td>10???</td>
<td></td>
</tr>
<tr class="odd">
<td>Packet length</td>
<td>22 bytes</td>
<td></td>
<td></td>
</tr>
</tbody>
</table>

>  **Text With GPS Position record (TwGPSRec)**

<table>
<tbody>
<tr class="odd">
<td><strong>Field</strong></td>
<td><strong>Type</strong></td>
<td><strong>Bytes</strong></td>
<td><strong>Note</strong></td>
</tr>
<tr class="even">
<td>Date and time</td>
<td>Int32</td>
<td>4</td>
<td>Timestamp since 2018.01.01 00:00:00 UTC in seconds</td>
</tr>
<tr class="odd">
<td>Latitude</td>
<td>Int32</td>
<td>4</td>
<td><p>166° 59’ 59’’ is converted to 1665959;</p>
<p>-23° 12‘ 44‘‘ is converted </p>
<p>-231244</p></td>
</tr>
<tr class="even">
<td>Longitude</td>
<td>Int32</td>
<td>4</td>
<td><p>166° 59’ 59’’ is converted to 1665959;</p>
<p>-23° 12‘ 44‘‘ is converted </p>
<p>-231244</p></td>
</tr>
<tr class="odd">
<td>Text</td>
<td>Byte[]</td>
<td>N</td>
<td>Text with length N</td>
</tr>
<tr class="even">
<td>Sens</td>
<td>SensRec</td>
<td>9</td>
<td>IDP Sensors data</td>
</tr>
<tr class="odd">
<td>Packet length</td>
<td></td>
<td></td>
<td></td>
</tr>
</tbody>
</table>

>  **Current position record (PosRec)**

<table>
<tbody>
<tr class="odd">
<td><strong>Field</strong></td>
<td><strong>Type</strong></td>
<td><strong>Bytes</strong></td>
<td><strong>Note</strong></td>
</tr>
<tr class="even">
<td>Content</td>
<td>Byte</td>
<td>1</td>
<td>Bit 7 </td>
</tr>
<tr class="odd">
<td>Timestamp</td>
<td>Int32</td>
<td>4</td>
<td>Timestamp</td>
</tr>
<tr class="even">
<td>Latitude</td>
<td>Int32</td>
<td>4</td>
<td><p>166° 59’ 59’’ is converted to 1665959;</p>
<p>-23° 12‘ 44‘‘ is converted </p>
<p>-231244</p></td>
</tr>
<tr class="odd">
<td>Longitude</td>
<td>Int32</td>
<td>4</td>
<td><p>166° 59’ 59’’ is converted to 1665959;</p>
<p>-23° 12‘ 44‘‘ is converted </p>
<p>-231244</p></td>
</tr>
<tr class="even">
<td>Speed</td>
<td>Uint16</td>
<td>2</td>
<td>Speed</td>
</tr>
<tr class="odd">
<td>Course </td>
<td>Uint16</td>
<td>2</td>
<td>Course</td>
</tr>
<tr class="even">
<td>Packet length</td>
<td>9 bytes</td>
<td><span id="anchor-14"></span></td>
<td></td>
</tr>
</tbody>
</table>

>  **Meteo data record (MeteoDRec) – checked 2.1**

|               |                       |             |                                                        |
|---------------|-----------------------|-------------|--------------------------------------------------------|
| **Field**     | **Type**              | **Bytes**   | **Note**                                               |
| Content       | Byte                  | 1           | Reserved                                               |
| Meteo data    | MeteoSRec\[\]         | (12 + N)\*X | List of meteo data from different sources based on cmd |
| Packet length | 1 + (12 + N)\*X bytes |             |                                                        |

>   **Meteo single record (MeteoSRec) – checked 2.1**

|               |          |                              |                                                                |
|---------------|----------|------------------------------|----------------------------------------------------------------|
| **Field**     | **Type** | **Bytes**                    | **Note**                                                       |
| **Content**   | **Byte** | **1**                        | **Reserved**                                                   |
| Name          | Byte     | 1+N                          | Meteo station name, First byte is the length of data           |
| Wind          | ushort   | 2                            | Wind value divided by 10 (Example 301/10 = 30.1 value)         |
| Gust          | ushort   | 2                            | Gust of wind value divided by 10 (Example 88/10 = 8.8 value)   |
| Direction     | ushort   | 2                            | Wind direction value divided by 10 (Example 3566/ 10 = 356.6 ) |
| Temperature   | ushort   | 2                            | Temperature value divided by 10 (Example 66/ 10 = 6.6 )        |
| Pressure      | ushort   | 2                            | Pressure value divided by 10 (Example 15233/10 = 1523.3)       |
| CreationDT    | Uint32   | 4                            | Timestamp since 2018.01.01 00:00:00 UTC in seconds             |
|               |          |                              |                                                                |
| Packet length |          | <span id="anchor-15"></span> |                                                                |

>  **Port Depart or Arrive (PortInOutRec) – checked 2.1**

<table>
<tbody>
<tr class="odd">
<td><strong>Field</strong></td>
<td><strong>Type</strong></td>
<td><strong>Bytes</strong></td>
<td><strong>Note</strong></td>
</tr>
<tr class="even">
<td>Content</td>
<td>Byte</td>
<td>1</td>
<td><p>Bit 2 – Status generation of record – 23.06.2019</p>
<p>Bit 1 - not in use( v. 2.1)</p>
<p>Bit 0 </p></td>
</tr>
<tr class="odd">
<td>Port Id</td>
<td>Ushort</td>
<td>2</td>
<td>Port Identifier</td>
</tr>
<tr class="even">
<td>Position</td>
<td>PosRec</td>
<td>18</td>
<td>GPS Position record and time</td>
</tr>
<tr class="odd">
<td>Packet length</td>
<td>3 ~ 21 bytes</td>
<td><span id="anchor-16"></span></td>
<td></td>
</tr>
</tbody>
</table>

>  **Position record (PosRec) – checked 2.1**

<table>
<tbody>
<tr class="odd">
<td><strong>Field</strong></td>
<td><strong>Type</strong></td>
<td><strong>Bytes</strong></td>
<td><strong>Note</strong></td>
</tr>
<tr class="even">
<td>Date and time</td>
<td>uInt32</td>
<td>4</td>
<td>Timestamp since 2018.01.01 00:00:00 UTC in seconds =&gt;
CreationDT</td>
</tr>
<tr class="odd">
<td>Latitude</td>
<td>Int32</td>
<td>4</td>
<td><p>166° 59’ 59’’ is converted to 1665959;</p>
<p>-23° 12‘ 44‘‘ is converted </p>
<p>-231244</p></td>
</tr>
<tr class="even">
<td>Longitude</td>
<td>Int32</td>
<td>4</td>
<td><p>166° 59’ 59’’ is converted to 1665959;</p>
<p>-23° 12‘ 44‘‘ is converted </p>
<p>-231244</p></td>
</tr>
<tr class="odd">
<td>Speed</td>
<td>Ushort</td>
<td>2</td>
<td>Speed</td>
</tr>
<tr class="even">
<td>Course</td>
<td>Ushort</td>
<td>2</td>
<td>Course</td>
</tr>
<tr class="odd">
<td>Packet length</td>
<td></td>
<td></td>
<td></td>
</tr>
</tbody>
</table>

>  **Sensors record (SensRec) – checked 2.7**

|                 |          |           |               |
|-----------------|----------|-----------|---------------|
| **Field**       | **Type** | **Bytes** | **Note**      |
| Temp – Analog 1 | Int32    | 4         | Analog input  |
| Analog 2        | Int32    | 4         | Analog input  |
| DIO             | Byte     | 1         | Digital IO    |
| Packet length   |          |           |               |

>  **Landing declaration (LDRec) **

<table>
<tbody>
<tr class="odd">
<td><strong>Field</strong></td>
<td><strong>Type</strong></td>
<td><strong>Bytes</strong></td>
<td><strong>Note</strong></td>
<td><strong>Version FVMS-M</strong></td>
</tr>
<tr class="even">
<td><strong>Content</strong></td>
<td><strong>Byte</strong></td>
<td><strong>1</strong></td>
<td>Bit 1 – Full or Send on parts </td>
<td><p>This filed is implemented from v 3.0.0.8</p>
<p>Bit 1 – 3.0.0.8</p>
<p> </p>
<p> </p></td>
</tr>
<tr class="odd">
<td>Tripnumber</td>
<td>Byte[]</td>
<td>~</td>
<td><p>Tripnumber </p></td>
<td></td>
</tr>
<tr class="even">
<td>FORecs</td>
<td>FORec[]</td>
<td>~</td>
<td>List of FORec </td>
<td></td>
</tr>
<tr class="odd">
<td>FCRecs</td>
<td>FCRec[]</td>
<td>~</td>
<td>List of FCRec</td>
<td></td>
</tr>
<tr class="even">
<td>Pos</td>
<td>PosRec</td>
<td>~</td>
<td>Date/ time, position</td>
<td></td>
</tr>
<tr class="odd">
<td>Sens</td>
<td>SensRec</td>
<td>9</td>
<td>Current state of sensors</td>
<td></td>
</tr>
<tr class="even">
<td>LDFGList</td>
<td>Char[]</td>
<td>21</td>
<td><p>List ot fg-&gt;seq and geareye</p></td>
<td>This field is implemented from v. 3.0.0.8</td>
</tr>
<tr class="odd">
<td>Packet length</td>
<td> </td>
<td></td>
<td></td>
<td></td>
</tr>
</tbody>
</table>

1.  1.  **Innovation Norway**

    1.  **Innovation Norway ASPS Full Data record (INDataRec)**

<table>
<tbody>
<tr class="odd">
<td><strong>Field</strong></td>
<td><strong>Type</strong></td>
<td><strong>Bytes</strong></td>
<td><strong>Note</strong></td>
<td><strong>Version 3DP-ASPS</strong></td>
</tr>
<tr class="even">
<td>Content</td>
<td>Byte</td>
<td>1</td>
<td><p>Bit3 –PosRec</p>
<p>Bit2 –OECB Data Rec</p>
<p>Bit1 – SSCB Data Rec</p>
<p>Bit0 – ASPS Data Rec </p></td>
<td><p> </p>
<p> </p>
<p> </p></td>
</tr>
<tr class="odd">
<td>ASPSDataRec</td>
<td>-</td>
<td>~</td>
<td></td>
<td></td>
</tr>
<tr class="even">
<td>SSCBDataRec</td>
<td>-</td>
<td>~</td>
<td></td>
<td></td>
</tr>
<tr class="odd">
<td>OECBDataRec</td>
<td>-</td>
<td>~</td>
<td></td>
<td></td>
</tr>
<tr class="even">
<td>Pos</td>
<td>PosRec</td>
<td>~</td>
<td>Date/ time, position</td>
<td>If PosRec is used -&gt; CreationDT is excluded</td>
</tr>
<tr class="odd">
<td>CreationDT</td>
<td>Uint32</td>
<td>4</td>
<td>Timestamp since 2018.01.01 00:00:00 UTC in seconds</td>
<td> If Creation is used -&gt; PosRec is excluded</td>
</tr>
<tr class="even">
<td>Packet length</td>
<td>~</td>
<td></td>
<td></td>
<td></td>
</tr>
</tbody>
</table>

>  **ASPS Data Record (ASPSDataRec)**

<table>
<tbody>
<tr class="odd">
<td><strong>Field</strong></td>
<td><strong>Type</strong></td>
<td><strong>Bytes</strong></td>
<td><strong>Note</strong></td>
<td><strong>Version 3DP-ASPS</strong></td>
</tr>
<tr class="even">
<td>Content</td>
<td>Byte</td>
<td>1</td>
<td><p>Bit0 –PosRec</p>
<p>Bit1 </p>
<p>Bit2 </p>
<p>Bit3</p>
<p>Bit4</p>
<p>Bit5</p>
<p>Bit6</p></td>
<td><p> </p>
<p> </p>
<p> </p></td>
</tr>
<tr class="odd">
<td>OnOff</td>
<td>Byte</td>
<td>1</td>
<td><p>Bit3 – PG(Power Generator)</p>
<p>Bit2 – MSPS(Main Ship Power Supply)</p>
<p>Bit1 – WG(WindGenerator)</p>
<p>Bit0 - SP (SolarPower) </p></td>
<td></td>
</tr>
<tr class="even">
<td>CreationDTL</td>
<td>Uint32</td>
<td>4</td>
<td>Timestamp since 2018.01.01 00:00:00 UTC in seconds</td>
<td>CreationDTL</td>
</tr>
<tr class="odd">
<td>SwitchState</td>
<td>Byte</td>
<td>1</td>
<td><p>Bit5 – SP7</p>
<p>Bit4 – SP6</p>
<p>Bit3 – SP5</p>
<p>Bit2 – SP4</p>
<p>Bit1 – SP3</p>
<p>Bit0 – SP2 </p></td>
<td></td>
</tr>
<tr class="even">
<td>PosRec</td>
<td>-</td>
<td>~</td>
<td><p>Position Rec</p></td>
<td>If PosRec is used -&gt; CreationDT is excluded</td>
</tr>
<tr class="odd">
<td>SolarCh</td>
<td><p>Victron Scharger</p>
<p>Rec</p></td>
<td>~</td>
<td>SolarCharger information</td>
<td></td>
</tr>
<tr class="even">
<td>WindGen</td>
<td><p>Victron</p>
<p>SH1Rec</p></td>
<td>~</td>
<td>Wind generator information</td>
<td></td>
</tr>
<tr class="odd">
<td>OutEnergy</td>
<td><p>Victron</p>
<p>SH2Rec</p></td>
<td>~</td>
<td>Output Energy information</td>
<td></td>
</tr>
<tr class="even">
<td>BrownEnergy</td>
<td><p>Victron</p>
<p>SH3Rec</p></td>
<td>~</td>
<td>Brown Energy information</td>
<td></td>
</tr>
<tr class="odd">
<td>AcuBat</td>
<td><p>Victron</p>
<p>SH4Rec</p></td>
<td>~</td>
<td>ACU information</td>
<td></td>
</tr>
<tr class="even">
<td>VSystem</td>
<td><p>Victron</p>
<p>System</p></td>
<td>~</td>
<td>Victron system information</td>
<td></td>
</tr>
<tr class="odd">
<td>Packet length</td>
<td> </td>
<td></td>
<td></td>
<td></td>
</tr>
</tbody>
</table>

>  **SSCB Data Record (SSCBDataRec)**

<table>
<tbody>
<tr class="odd">
<td><strong>Field</strong></td>
<td><strong>Type</strong></td>
<td><strong>Bytes</strong></td>
<td><strong>Note</strong></td>
<td><strong>Version 3DP-ASPS</strong></td>
</tr>
<tr class="even">
<td>Content</td>
<td>Byte</td>
<td>1</td>
<td>Bit0 – PosRec </td>
<td><p> </p>
<p> </p>
<p> </p></td>
</tr>
<tr class="odd">
<td>OnOff</td>
<td>Byte</td>
<td>1</td>
<td></td>
<td>Not in use</td>
</tr>
<tr class="even">
<td>CreationDTL</td>
<td>Uint32</td>
<td>4</td>
<td>Timestamp since 2018.01.01 00:00:00 UTC in seconds</td>
<td>CreationDTL</td>
</tr>
<tr class="odd">
<td>PosRec</td>
<td>PosRec</td>
<td>~</td>
<td><p>Position Rec</p></td>
<td>If PosRec is used -&gt; CreationDT is excluded</td>
</tr>
<tr class="even">
<td>SensorsData</td>
<td><p>Dpvinkxe</p>
<p>Rec</p></td>
<td>~</td>
<td></td>
<td>Sensors data</td>
</tr>
<tr class="odd">
<td>Packet length</td>
<td> </td>
<td></td>
<td></td>
<td></td>
</tr>
</tbody>
</table>

>  <span id="anchor-17"></span>**OECB Data Record
            > (OECBDataRec)**

<table>
<tbody>
<tr class="odd">
<td><strong>Field</strong></td>
<td><strong>Type</strong></td>
<td><strong>Bytes</strong></td>
<td><strong>Note</strong></td>
<td><strong>Version 3DP-ASPS</strong></td>
</tr>
<tr class="even">
<td>Content</td>
<td>Byte</td>
<td>1</td>
<td><p>Bit0 – PosRec </p>
<p>Bit1 – Airmar</p>
<p>Bit2 – Aquaread</p>
<p>Bit3 – Escort </p>
<p>Bit4 – Dahua </p></td>
<td><p> </p>
<p> </p>
<p> </p></td>
</tr>
<tr class="odd">
<td>OnOff</td>
<td>Byte</td>
<td>1</td>
<td></td>
<td>Not in use</td>
</tr>
<tr class="even">
<td>CreationDTL</td>
<td>Uint32</td>
<td>4</td>
<td>Timestamp since 2018.01.01 00:00:00 UTC in seconds</td>
<td>If CreationDTL Is used -&gt; PosRec is excluded</td>
</tr>
<tr class="odd">
<td>PosRec</td>
<td>PosRec</td>
<td>~</td>
<td><p>Position Rec</p></td>
<td>If PosRec is used -&gt; CreationDT is excluded</td>
</tr>
<tr class="even">
<td>Aread</td>
<td><p>Aquaread</p>
<p>Rec</p></td>
<td>~</td>
<td>Aquaread data</td>
<td></td>
</tr>
<tr class="odd">
<td>Amar</td>
<td><p>Airmar</p>
<p>Rec</p></td>
<td>~</td>
<td>Airmar data</td>
<td></td>
</tr>
<tr class="even">
<td>Dahua</td>
<td><p>Dahua</p>
<p>Rec</p></td>
<td>~</td>
<td>Dahua data</td>
<td></td>
</tr>
<tr class="odd">
<td>Escort</td>
<td><p>Escort</p>
<p>Rec</p></td>
<td>~</td>
<td>Escort data</td>
<td></td>
</tr>
<tr class="even">
<td>Packet length</td>
<td> </td>
<td></td>
<td></td>
<td></td>
</tr>
</tbody>
</table>

>  **VictronSH1 (VictronSH1Rec) - WindGenerator**

<table>
<tbody>
<tr class="odd">
<td><strong>Field</strong></td>
<td><strong>Type</strong></td>
<td><strong>Bytes</strong></td>
<td><strong>Note</strong></td>
<td><strong>Version 3DP-ASPS</strong></td>
</tr>
<tr class="even">
<td>Content</td>
<td>Byte</td>
<td>1</td>
<td><p>/// SH1 SH2 SH3 SH4 SP SYS</p>
<p>/// Bit 0 -&gt; 1 0 0 0 0 0</p>
<p>/// Bit 1 -&gt; 0 1 0 0 0 0</p>
<p>/// Bit 2 -&gt; 0 0 1 0 0 0</p>
<p>/// Bit 3 -&gt; 0 0 0 1 0 0</p>
<p>/// Bit 4 -&gt; 0 0 0 0 1 0</p>
<p>/// Bit 5 -&gt; 0 0 0 0 0 1</p></td>
<td><p> </p>
<p> </p>
<p> </p></td>
</tr>
<tr class="odd">
<td>CreationDTL</td>
<td>Uint32</td>
<td>4</td>
<td>Timestamp since 2018.01.01 00:00:00 UTC in seconds</td>
<td>CreationDTL</td>
</tr>
<tr class="even">
<td>CustomName</td>
<td>Char[]</td>
<td>N</td>
<td><p>Byte 0 – size of the following data (Nbytes) –
customname</p></td>
<td>Sim_SH1</td>
</tr>
<tr class="odd">
<td>DC0Current</td>
<td>Float</td>
<td>4</td>
<td>Current</td>
<td></td>
</tr>
<tr class="even">
<td>DC0Voltage</td>
<td>Float</td>
<td>4</td>
<td>Voltage</td>
<td></td>
</tr>
<tr class="odd">
<td>DC0Power</td>
<td>Float</td>
<td>4</td>
<td>Power</td>
<td></td>
</tr>
<tr class="even">
<td>DC0Temp</td>
<td>Float</td>
<td>4</td>
<td>Temperature</td>
<td></td>
</tr>
<tr class="odd">
<td>DC1Voltage</td>
<td>Float</td>
<td>4</td>
<td>Voltage ??? </td>
<td></td>
</tr>
<tr class="even">
<td>HisChargedEnergy</td>
<td>Float</td>
<td>4</td>
<td>History Charged Energy</td>
<td></td>
</tr>
<tr class="odd">
<td>HisDeepestDischarge</td>
<td>Float</td>
<td>4</td>
<td>History Deepest Discharge</td>
<td></td>
</tr>
<tr class="even">
<td>HisDischargedEnergy</td>
<td>Float</td>
<td>4</td>
<td>History Discharged Energy</td>
<td></td>
</tr>
<tr class="odd">
<td>HisMaximumVoltage</td>
<td>Float</td>
<td>4</td>
<td>History maximum voltage</td>
<td></td>
</tr>
<tr class="even">
<td><p>HisMinimumStarer</p>
<p>Voltage</p></td>
<td>Float</td>
<td>4 </td>
<td>History minimum starter voltage</td>
<td></td>
</tr>
<tr class="odd">
<td>HisMinimumVoltage</td>
<td>Float</td>
<td>4</td>
<td>History minimum voltage</td>
<td></td>
</tr>
<tr class="even">
<td>HisTotalAhDrawn</td>
<td>Float</td>
<td>4</td>
<td>History total AH Drawn</td>
<td></td>
</tr>
<tr class="odd">
<td>SOC</td>
<td>Float</td>
<td>4</td>
<td>SOC</td>
<td></td>
</tr>
<tr class="even">
<td>Packet length</td>
<td> </td>
<td></td>
<td></td>
<td></td>
</tr>
</tbody>
</table>

>  **VictronSH2 (VictronSH2Rec) – Output Energy**

<table>
<tbody>
<tr class="odd">
<td><strong>Field</strong></td>
<td><strong>Type</strong></td>
<td><strong>Bytes</strong></td>
<td><strong>Note</strong></td>
<td><strong>Version 3DP-ASPS</strong></td>
</tr>
<tr class="even">
<td>Content</td>
<td>Byte</td>
<td>1</td>
<td><p>/// SH1 SH2 SH3 SH4 SP SYS</p>
<p>/// Bit 0 -&gt; 1 0 0 0 0 0</p>
<p>/// Bit 1 -&gt; 0 1 0 0 0 0</p>
<p>/// Bit 2 -&gt; 0 0 1 0 0 0</p>
<p>/// Bit 3 -&gt; 0 0 0 1 0 0</p>
<p>/// Bit 4 -&gt; 0 0 0 0 1 0</p>
<p>/// Bit 5 -&gt; 0 0 0 0 0 1</p></td>
<td><p> </p>
<p> </p>
<p> </p></td>
</tr>
<tr class="odd">
<td>CreationDTL</td>
<td>Uint32</td>
<td>4</td>
<td>Timestamp since 2018.01.01 00:00:00 UTC in seconds</td>
<td>CreationDTL</td>
</tr>
<tr class="even">
<td>CustomName</td>
<td>Char[]</td>
<td>N</td>
<td><p>Byte 0 – size of the following data (Nbytes) –
customname</p></td>
<td>Sim_SH2</td>
</tr>
<tr class="odd">
<td>DC0Current</td>
<td>Float</td>
<td>4</td>
<td>Current</td>
<td></td>
</tr>
<tr class="even">
<td>DC0Voltage</td>
<td>Float</td>
<td>4</td>
<td>Voltage</td>
<td></td>
</tr>
<tr class="odd">
<td>DC0Power</td>
<td>Float</td>
<td>4</td>
<td>Power</td>
<td></td>
</tr>
<tr class="even">
<td>DC0Temp</td>
<td>Float</td>
<td>4</td>
<td>Temperature</td>
<td></td>
</tr>
<tr class="odd">
<td>DC1Voltage</td>
<td>Float</td>
<td>4</td>
<td>Voltage ??? </td>
<td></td>
</tr>
<tr class="even">
<td>HisChargedEnergy</td>
<td>Float</td>
<td>4</td>
<td>History Charged Energy</td>
<td></td>
</tr>
<tr class="odd">
<td>HisDeepestDischarge</td>
<td>Float</td>
<td>4</td>
<td>History Deepest Discharge</td>
<td></td>
</tr>
<tr class="even">
<td>HisDischargedEnergy</td>
<td>Float</td>
<td>4</td>
<td>History Discharged Energy</td>
<td></td>
</tr>
<tr class="odd">
<td>HisMaximumVoltage</td>
<td>Float</td>
<td>4</td>
<td>History maximum voltage</td>
<td></td>
</tr>
<tr class="even">
<td><p>HisMinimumStarer</p>
<p>Voltage</p></td>
<td>Float</td>
<td>4 </td>
<td>History minimum starter voltage</td>
<td></td>
</tr>
<tr class="odd">
<td>HisMinimumVoltage</td>
<td>Float</td>
<td>4</td>
<td>History minimum voltage</td>
<td></td>
</tr>
<tr class="even">
<td>HisTotalAhDrawn</td>
<td>Float</td>
<td>4</td>
<td>History total AH Drawn</td>
<td></td>
</tr>
<tr class="odd">
<td>SOC</td>
<td>Float</td>
<td>4</td>
<td>SOC</td>
<td></td>
</tr>
<tr class="even">
<td>Packet length</td>
<td> </td>
<td></td>
<td></td>
<td></td>
</tr>
</tbody>
</table>

** **

>  **VictronSH3 (VictronSH3Rec) – Brown Energy**

<table>
<tbody>
<tr class="odd">
<td><strong>Field</strong></td>
<td><strong>Type</strong></td>
<td><strong>Bytes</strong></td>
<td><strong>Note</strong></td>
<td><strong>Version 3DP-ASPS</strong></td>
</tr>
<tr class="even">
<td>Content</td>
<td>Byte</td>
<td>1</td>
<td><p>/// SH1 SH2 SH3 SH4 SP SYS</p>
<p>/// Bit 0 -&gt; 1 0 0 0 0 0</p>
<p>/// Bit 1 -&gt; 0 1 0 0 0 0</p>
<p>/// Bit 2 -&gt; 0 0 1 0 0 0</p>
<p>/// Bit 3 -&gt; 0 0 0 1 0 0</p>
<p>/// Bit 4 -&gt; 0 0 0 0 1 0</p>
<p>/// Bit 5 -&gt; 0 0 0 0 0 1</p></td>
<td><p> </p>
<p> </p>
<p> </p></td>
</tr>
<tr class="odd">
<td>CreationDTL</td>
<td>Uint32</td>
<td>4</td>
<td>Timestamp since 2018.01.01 00:00:00 UTC in seconds</td>
<td>CreationDTL</td>
</tr>
<tr class="even">
<td>CustomName</td>
<td>Char[]</td>
<td>N</td>
<td><p>Byte 0 – size of the following data (Nbytes) –
customname</p></td>
<td>Sim_SH3</td>
</tr>
<tr class="odd">
<td>DC0Current</td>
<td>Float</td>
<td>4</td>
<td>Current</td>
<td></td>
</tr>
<tr class="even">
<td>DC0Voltage</td>
<td>Float</td>
<td>4</td>
<td>Voltage</td>
<td></td>
</tr>
<tr class="odd">
<td>DC0Power</td>
<td>Float</td>
<td>4</td>
<td>Power</td>
<td></td>
</tr>
<tr class="even">
<td>DC0Temp</td>
<td>Float</td>
<td>4</td>
<td>Temperature</td>
<td></td>
</tr>
<tr class="odd">
<td>DC1Voltage</td>
<td>Float</td>
<td>4</td>
<td>Voltage ??? </td>
<td></td>
</tr>
<tr class="even">
<td>HisChargedEnergy</td>
<td>Float</td>
<td>4</td>
<td>History Charged Energy</td>
<td></td>
</tr>
<tr class="odd">
<td>HisDeepestDischarge</td>
<td>Float</td>
<td>4</td>
<td>History Deepest Discharge</td>
<td></td>
</tr>
<tr class="even">
<td>HisDischargedEnergy</td>
<td>Float</td>
<td>4</td>
<td>History Discharged Energy</td>
<td></td>
</tr>
<tr class="odd">
<td>HisMaximumVoltage</td>
<td>Float</td>
<td>4</td>
<td>History maximum voltage</td>
<td></td>
</tr>
<tr class="even">
<td><p>HisMinimumStarer</p>
<p>Voltage</p></td>
<td>Float</td>
<td>4 </td>
<td>History minimum starter voltage</td>
<td></td>
</tr>
<tr class="odd">
<td>HisMinimumVoltage</td>
<td>Float</td>
<td>4</td>
<td>History minimum voltage</td>
<td></td>
</tr>
<tr class="even">
<td>HisTotalAhDrawn</td>
<td>Float</td>
<td>4</td>
<td>History total AH Drawn</td>
<td></td>
</tr>
<tr class="odd">
<td>SOC</td>
<td>Float</td>
<td>4</td>
<td>SOC</td>
<td></td>
</tr>
<tr class="even">
<td>Packet length</td>
<td> </td>
<td></td>
<td></td>
<td></td>
</tr>
</tbody>
</table>

>  **VictronSH4 (VictronSH4Rec) – ACU**

<table>
<tbody>
<tr class="odd">
<td><strong>Field</strong></td>
<td><strong>Type</strong></td>
<td><strong>Bytes</strong></td>
<td><strong>Note</strong></td>
<td><strong>Version 3DP-ASPS</strong></td>
</tr>
<tr class="even">
<td>Content</td>
<td>Byte</td>
<td>1</td>
<td><p>/// SH1 SH2 SH3 SH4 SP SYS</p>
<p>/// Bit 0 -&gt; 1 0 0 0 0 0</p>
<p>/// Bit 1 -&gt; 0 1 0 0 0 0</p>
<p>/// Bit 2 -&gt; 0 0 1 0 0 0</p>
<p>/// Bit 3 -&gt; 0 0 0 1 0 0</p>
<p>/// Bit 4 -&gt; 0 0 0 0 1 0</p>
<p>/// Bit 5 -&gt; 0 0 0 0 0 1</p></td>
<td><p> </p>
<p> </p>
<p> </p></td>
</tr>
<tr class="odd">
<td>CreationDTL</td>
<td>Uint32</td>
<td>4</td>
<td>Timestamp since 2018.01.01 00:00:00 UTC in seconds</td>
<td>CreationDTL</td>
</tr>
<tr class="even">
<td>CustomName</td>
<td>Char[]</td>
<td>N</td>
<td><p>Byte 0 – size of the following data (Nbytes) –
customname</p></td>
<td>Sim_SH4</td>
</tr>
<tr class="odd">
<td>DC0Current</td>
<td>Float</td>
<td>4</td>
<td>Current</td>
<td></td>
</tr>
<tr class="even">
<td>DC0Voltage</td>
<td>Float</td>
<td>4</td>
<td>Voltage</td>
<td></td>
</tr>
<tr class="odd">
<td>DC0Power</td>
<td>Float</td>
<td>4</td>
<td>Power</td>
<td></td>
</tr>
<tr class="even">
<td>DC0Temp</td>
<td>Float</td>
<td>4</td>
<td>Temperature</td>
<td></td>
</tr>
<tr class="odd">
<td>DC1Voltage</td>
<td>Float</td>
<td>4</td>
<td>Voltage ??? </td>
<td></td>
</tr>
<tr class="even">
<td>HisChargedEnergy</td>
<td>Float</td>
<td>4</td>
<td>History Charged Energy</td>
<td></td>
</tr>
<tr class="odd">
<td>HisDeepestDischarge</td>
<td>Float</td>
<td>4</td>
<td>History Deepest Discharge</td>
<td></td>
</tr>
<tr class="even">
<td>HisDischargedEnergy</td>
<td>Float</td>
<td>4</td>
<td>History Discharged Energy</td>
<td></td>
</tr>
<tr class="odd">
<td>HisMaximumVoltage</td>
<td>Float</td>
<td>4</td>
<td>History maximum voltage</td>
<td></td>
</tr>
<tr class="even">
<td><p>HisMinimumStarer</p>
<p>Voltage</p></td>
<td>Float</td>
<td>4 </td>
<td>History minimum starter voltage</td>
<td></td>
</tr>
<tr class="odd">
<td>HisMinimumVoltage</td>
<td>Float</td>
<td>4</td>
<td>History minimum voltage</td>
<td></td>
</tr>
<tr class="even">
<td>HisTotalAhDrawn</td>
<td>Float</td>
<td>4</td>
<td>History total AH Drawn</td>
<td></td>
</tr>
<tr class="odd">
<td>SOC</td>
<td>Float</td>
<td>4</td>
<td>SOC</td>
<td></td>
</tr>
<tr class="even">
<td>Packet length</td>
<td> </td>
<td></td>
<td></td>
<td></td>
</tr>
</tbody>
</table>

>  **VictronSCharger (VictronSChargerRec) – Solar Charger**

<table>
<tbody>
<tr class="odd">
<td><strong>Field</strong></td>
<td><strong>Type</strong></td>
<td><strong>Bytes</strong></td>
<td><strong>Note</strong></td>
<td><strong>Version 3DP-ASPS</strong></td>
</tr>
<tr class="even">
<td>Content</td>
<td>Byte</td>
<td>1</td>
<td><p>/// SH1 SH2 SH3 SH4 SP SYS</p>
<p>/// Bit 0 -&gt; 1 0 0 0 0 0</p>
<p>/// Bit 1 -&gt; 0 1 0 0 0 0</p>
<p>/// Bit 2 -&gt; 0 0 1 0 0 0</p>
<p>/// Bit 3 -&gt; 0 0 0 1 0 0</p>
<p>/// Bit 4 -&gt; 0 0 0 0 1 0</p>
<p>/// Bit 5 -&gt; 0 0 0 0 0 1</p></td>
<td><p> </p>
<p> </p>
<p> </p></td>
</tr>
<tr class="odd">
<td>CreationDTL</td>
<td>Uint32</td>
<td>4</td>
<td>Timestamp since 2018.01.01 00:00:00 UTC in seconds</td>
<td>CreationDTL</td>
</tr>
<tr class="even">
<td>CustomName</td>
<td>Char[]</td>
<td>N</td>
<td><p>Byte 0 – size of the following data (Nbytes) –
customname</p></td>
<td>Sim_SH3</td>
</tr>
<tr class="odd">
<td>DC0Current</td>
<td>Float</td>
<td>4</td>
<td>Current</td>
<td></td>
</tr>
<tr class="even">
<td>DC0Voltage</td>
<td>Float</td>
<td>4</td>
<td>Voltage</td>
<td></td>
</tr>
<tr class="odd">
<td>PvV</td>
<td>Float</td>
<td>4</td>
<td>Pv Voltage</td>
<td></td>
</tr>
<tr class="even">
<td>YieldPower</td>
<td>Float</td>
<td>4</td>
<td>Yiled Power</td>
<td></td>
</tr>
<tr class="odd">
<td>YieldSystem</td>
<td>Float</td>
<td>4</td>
<td>Yiled System</td>
<td></td>
</tr>
<tr class="even">
<td>YieldUser</td>
<td>Char[]</td>
<td>N</td>
<td><p>Byte 0 – size of the following data (Nbytes) – yielduser</p></td>
<td></td>
</tr>
<tr class="odd">
<td>HisOAMaxBatteryVoltage</td>
<td>Float</td>
<td>4</td>
<td>Overall history max battery voltage</td>
<td></td>
</tr>
<tr class="even">
<td>HisOAMaxPvVoltage</td>
<td>Float</td>
<td>4</td>
<td>Overall history max Pv Voltage</td>
<td></td>
</tr>
<tr class="odd">
<td>HisOAMinBatteryVoltage</td>
<td>Float</td>
<td>4</td>
<td>Overall history min battery voltage</td>
<td></td>
</tr>
<tr class="even">
<td>Packet length</td>
<td> </td>
<td></td>
<td></td>
<td></td>
</tr>
</tbody>
</table>

>  **VictronSystem (VictronSystemRec) – Victron System
            > information**

<table>
<tbody>
<tr class="odd">
<td><strong>Field</strong></td>
<td><strong>Type</strong></td>
<td><strong>Bytes</strong></td>
<td><strong>Note</strong></td>
<td><strong>Version 3DP-ASPS</strong></td>
</tr>
<tr class="even">
<td>Content</td>
<td>Byte</td>
<td>1</td>
<td><p>/// SH1 SH2 SH3 SH4 SP SYS</p>
<p>/// Bit 0 -&gt; 1 0 0 0 0 0</p>
<p>/// Bit 1 -&gt; 0 1 0 0 0 0</p>
<p>/// Bit 2 -&gt; 0 0 1 0 0 0</p>
<p>/// Bit 3 -&gt; 0 0 0 1 0 0</p>
<p>/// Bit 4 -&gt; 0 0 0 0 1 0</p>
<p>/// Bit 5 -&gt; 0 0 0 0 0 1</p></td>
<td><p> </p>
<p> </p>
<p> </p></td>
</tr>
<tr class="odd">
<td>CreationDTL</td>
<td>Uint32</td>
<td>4</td>
<td>Timestamp since 2018.01.01 00:00:00 UTC in seconds</td>
<td>CreationDTL</td>
</tr>
<tr class="even">
<td>DC0BatteryCurrent</td>
<td>Float</td>
<td>4</td>
<td>Battery Current</td>
<td></td>
</tr>
<tr class="odd">
<td>DC0BatteryPower</td>
<td>Float</td>
<td>4</td>
<td>Battery Power</td>
<td></td>
</tr>
<tr class="even">
<td>DC0BatteryVoltage</td>
<td>Float</td>
<td>4</td>
<td>Battery Voltage</td>
<td></td>
</tr>
<tr class="odd">
<td>DC0BatterySoc</td>
<td>Float</td>
<td>4</td>
<td>Battery SOC</td>
<td></td>
</tr>
<tr class="even">
<td>Packet length</td>
<td> </td>
<td></td>
<td></td>
<td></td>
</tr>
</tbody>
</table>

>  **DpvinkxeRec – BeanAir sensors data**

|                 |          |           |                                                           |                           |
|-----------------|----------|-----------|-----------------------------------------------------------|---------------------------|
| **Field**       | **Type** | **Bytes** | **Note**                                                  | **Version 3DP-ASPS**      |
| Content         | Byte     | 1         |                                                           |  Not in use               |
| CreationDTL     | Uint32   | 4         | Timestamp since 2018.01.01 00:00:00 UTC in seconds        | CreationDTL               |
| Id              | Int      | 4         | Identifier                                                | Id from SSCB local DB ??? |
| Ba_device_id    | int      | 4         | Identifier                                                | Device Id from BeanAir    |
| SensorName      | Char\[\] | ~         | Byte 0 – size of the following data (Nbytes) – sensorname | Sensor name               |
| SensorMac       | Char\[\] | ~         | Byte 0 – size of the following data (Nbytes) – sensor mac | Sensor BA mac             |
| MeasurmentCount | Byte     | 1         | Count of measurements                                     |                           |
| Measurements    | MRec\[\] | ~         | List of MRec                                              | Measurments               |
| Packet length   |          |           |                                                           |                           |

>  **MRec – BeanAir sensor measurment**

|                     |          |           |                                                    |                      |
|---------------------|----------|-----------|----------------------------------------------------|----------------------|
| **Field**           | **Type** | **Bytes** | **Note**                                           | **Version 3DP-ASPS** |
| Content             | Byte     | 1         |                                                    |                      |
| CreationDTL         | Uint32   | 4         | Timestamp since 2018.01.01 00:00:00 UTC in seconds | CreationDTL          |
| Ch_X_g\_            | Float    | 4         | AX-3D Ch_X_g\_                                     |                      |
| Ch_Y_g\_            | Float    | 4         | AX-3D Ch_Y_g\_                                     |                      |
| Ch_Z_g\_            | Float    | 4         | AX-3D Ch_Z_g\_                                     |                      |
| Measures_Ch_X_deg\_ | Float    | 4         | Inc Measures_Ch_X_deg                              |                      |
| Ch_Y_deg\_          | Float    | 4         | Inc Ch_Y_deg                                       |                      |
| Temp                | Float    | 4         | OneT temperature                                   |                      |
| Packet length       |  29      |           |                                                    |                      |

>  **AquareadRec – OECB Aquaread data**

|               |          |           |                                                    |                      |
|---------------|----------|-----------|----------------------------------------------------|----------------------|
| **Field**     | **Type** | **Bytes** | **Note**                                           | **Version 3DP-ASPS** |
| Content       | Byte     | 1         |                                                    | Not in use           |
| CreationDTL   | Uint32   | 4         | Timestamp since 2018.01.01 00:00:00 UTC in seconds | CreationDTL          |
| Temp          | Float    | 4         | Water temperature                                  |                      |
| Baromb        | Float    | 4         | Barometric pressure                                |                      |
| pH            | Float    | 4         | Water pH                                           |                      |
| phMv          | Float    | 4         | Water pHmV                                         |                      |
| Ecu           | Float    | 4         | Ecu                                                |                      |
| ResO          | Float    | 4         | ResO                                               |                      |
| TDS           | Float    | 4         |                                                    |                      |
| Sal           | Float    | 4         | Salinity                                           |                      |
| SSG           | Float    | 4         |                                                    |                      |
| Unnamed       | Float    | 4         | ??? depth?                                         |                      |
| Packet length |          |           |                                                    |                      |

>  **AirmarRec – OECB Airmar data**

<table>
<tbody>
<tr class="odd">
<td><strong>Field</strong></td>
<td><strong>Type</strong></td>
<td><strong>Bytes</strong></td>
<td><strong>Note</strong></td>
<td><strong>Version 3DP-ASPS</strong></td>
</tr>
<tr class="even">
<td>Content</td>
<td>Byte</td>
<td>1</td>
<td></td>
<td></td>
</tr>
<tr class="odd">
<td>UnixTimestamp</td>
<td>Int</td>
<td>4</td>
<td></td>
<td></td>
</tr>
<tr class="even">
<td>Lon</td>
<td>Float</td>
<td>4</td>
<td>Longitude</td>
<td></td>
</tr>
<tr class="odd">
<td>Lat</td>
<td>Float</td>
<td>4</td>
<td>Latitude</td>
<td></td>
</tr>
<tr class="even">
<td>GPSQuality</td>
<td>Float</td>
<td>4</td>
<td>GpsQuality</td>
<td></td>
</tr>
<tr class="odd">
<td>GPSUnit</td>
<td>Char[]</td>
<td>4</td>
<td><p> Byte 0 – size of the following data (Nbytes) – gpsunit</p></td>
<td></td>
</tr>
<tr class="even">
<td>SatNV</td>
<td>Float</td>
<td>4</td>
<td>GPS Sat in view</td>
<td></td>
</tr>
<tr class="odd">
<td>Hdop</td>
<td>Float</td>
<td>4</td>
<td>GPS Hdop</td>
<td></td>
</tr>
<tr class="even">
<td>Altitude</td>
<td>Float</td>
<td>4</td>
<td>GPS altitude</td>
<td></td>
</tr>
<tr class="odd">
<td>Course</td>
<td>Float</td>
<td>4</td>
<td>GPS Course</td>
<td></td>
</tr>
<tr class="even">
<td>Speed</td>
<td>Float</td>
<td>4</td>
<td>GPS Speed</td>
<td></td>
</tr>
<tr class="odd">
<td>Bpressure</td>
<td>Float</td>
<td>4</td>
<td>Barometric pressure</td>
<td></td>
</tr>
<tr class="even">
<td>BPUnit</td>
<td>Byte</td>
<td>1</td>
<td>ASCII char – measurement unit</td>
<td></td>
</tr>
<tr class="odd">
<td>BpressureInch</td>
<td>Float</td>
<td>4 </td>
<td>Barometric pressure in inches</td>
<td></td>
</tr>
<tr class="even">
<td>BPUnitInch</td>
<td>Byte</td>
<td>1</td>
<td>ASCII char – measurement unit</td>
<td></td>
</tr>
<tr class="odd">
<td>AirTemp</td>
<td>Float</td>
<td>4</td>
<td>Air temperature</td>
<td></td>
</tr>
<tr class="even">
<td>ATUnit</td>
<td>Byte</td>
<td>1</td>
<td>ASCII char – measurement unit</td>
<td></td>
</tr>
<tr class="odd">
<td>WaterTemp</td>
<td>Float</td>
<td>4</td>
<td>Water temperature</td>
<td></td>
</tr>
<tr class="even">
<td>WTUnit</td>
<td>Byte</td>
<td>1</td>
<td>ASCII char – measurement unit</td>
<td></td>
</tr>
<tr class="odd">
<td>RHumidity</td>
<td>Float</td>
<td>4</td>
<td>Relative humidity</td>
<td></td>
</tr>
<tr class="even">
<td>AHumidity</td>
<td>Float</td>
<td>4</td>
<td>Absolute humidity</td>
<td></td>
</tr>
<tr class="odd">
<td>DewPoint</td>
<td>Float</td>
<td>4</td>
<td>Dew Point</td>
<td></td>
</tr>
<tr class="even">
<td>DPUnit</td>
<td>Byte</td>
<td>1</td>
<td>ASCII char – measurement unit</td>
<td></td>
</tr>
<tr class="odd">
<td>WDirTrue</td>
<td>Float</td>
<td>4</td>
<td>Wind direction true</td>
<td></td>
</tr>
<tr class="even">
<td>WDTUnit</td>
<td>Byte</td>
<td>1</td>
<td>ASCII char – measurement unit</td>
<td></td>
</tr>
<tr class="odd">
<td>WDirMagn</td>
<td>Float</td>
<td>4</td>
<td>Wind direction magnetic</td>
<td></td>
</tr>
<tr class="even">
<td>WDMUnit</td>
<td>Byte</td>
<td>1</td>
<td>ASCII char – measurement unit</td>
<td></td>
</tr>
<tr class="odd">
<td>WSpeedKn</td>
<td>Float</td>
<td>4</td>
<td>Wind speed in knots</td>
<td></td>
</tr>
<tr class="even">
<td>WSKUnit</td>
<td>Byte</td>
<td>1</td>
<td>ASCII char – measurement unit</td>
<td></td>
</tr>
<tr class="odd">
<td>WSpeedMS</td>
<td>Float</td>
<td>4 </td>
<td>Wind speed in m/s</td>
<td></td>
</tr>
<tr class="even">
<td>WSMSUnit</td>
<td>Byte</td>
<td>1</td>
<td>ASCII char – measurement unit</td>
<td></td>
</tr>
<tr class="odd">
<td>Heading</td>
<td>Float</td>
<td>4 </td>
<td>Heading</td>
<td></td>
</tr>
<tr class="even">
<td>Pitch</td>
<td>Float</td>
<td>4 </td>
<td>Pitch</td>
<td></td>
</tr>
<tr class="odd">
<td>PitchUnit</td>
<td>Byte</td>
<td>1</td>
<td>ASCII char – measurement unit</td>
<td></td>
</tr>
<tr class="even">
<td>Roll</td>
<td>Float</td>
<td>4</td>
<td>Roll</td>
<td></td>
</tr>
<tr class="odd">
<td>RollUnit</td>
<td>Byte</td>
<td>1</td>
<td>ASCII char – measurement unit</td>
<td></td>
</tr>
<tr class="even">
<td>Packet length</td>
<td> </td>
<td></td>
<td></td>
<td></td>
</tr>
</tbody>
</table>

>  **DahuaRec – OECB Dahua data**

<table>
<tbody>
<tr class="odd">
<td><strong>Field</strong></td>
<td><strong>Type</strong></td>
<td><strong>Bytes</strong></td>
<td><strong>Note</strong></td>
<td><strong>Version 3DP-ASPS</strong></td>
</tr>
<tr class="even">
<td>Content</td>
<td>Byte</td>
<td>1</td>
<td></td>
<td>Not in use</td>
</tr>
<tr class="odd">
<td>FilePath</td>
<td>Char[]</td>
<td>~</td>
<td><p>Byte 0 – size of the following data (Nbytes) – filepath</p></td>
<td></td>
</tr>
<tr class="even">
<td>ReceivedAtASPSDTL</td>
<td>Uint32</td>
<td>4</td>
<td>Timestamp since 2018.01.01 00:00:00 UTC in seconds. There is no data
of measurement from OECB</td>
<td></td>
</tr>
</tbody>
</table>

>  **EscortRec – OECB Escort data**

<table>
<tbody>
<tr class="odd">
<td><strong>Field</strong></td>
<td><strong>Type</strong></td>
<td><strong>Bytes</strong></td>
<td><strong>Note</strong></td>
<td><strong>Version 3DP-ASPS</strong></td>
</tr>
<tr class="even">
<td>Content</td>
<td>Byte</td>
<td>1</td>
<td></td>
<td>Not in use</td>
</tr>
<tr class="odd">
<td>Tag_id</td>
<td>Char[]</td>
<td>~</td>
<td><p>Byte 0 – size of the following data (Nbytes) – tag_id</p></td>
<td></td>
</tr>
<tr class="even">
<td>Event</td>
<td>Byte</td>
<td>1</td>
<td>0/1</td>
<td></td>
</tr>
<tr class="odd">
<td>ReceivedAtASPSDTL</td>
<td>Uint32</td>
<td>4</td>
<td>Timestamp since 2018.01.01 00:00:00 UTC in seconds. There is no data
of measurement from OECB</td>
<td></td>
</tr>
</tbody>
</table>

1.  1.  **Additional data structures**

>  **Conversion factors (ConversionFactor) – checked 2.1**

|                 |          |                              |                           |
|-----------------|----------|------------------------------|---------------------------|
| **Field**       | **Type** | **Bytes**                    | **Note**                  |
| Species_id      | Int32    | 4                            | FK Species -\> id         |
| Presentation_id | Int32    | 4                            | FK FPresentation - id     |
| Condition_id    | Int32    | 4                            | FK FCondition -\> id      |
| Coefficient     | Float    | 4                            | Coefficient of conversion |
| Type            | Byte     | 1                            |                           |
| Packet length   | bytes    | <span id="anchor-18"></span> |                           |

>  **Country codes (CountrCodeM49) – checked 2.1**

|               |          |                              |                             |
|---------------|----------|------------------------------|-----------------------------|
| **Field**     | **Type** | **Bytes**                    | **Note**                    |
| CountryOrArea | String   | \-                           | Country or area description |
| M49           | String   | \-                           | M49 codes                   |
| ISOAlpha3Code | String   | \-                           | Country ISO alpha 3 code    |
| Packet length | bytes    | <span id="anchor-19"></span> |                             |

>  **Crew nationality (CrewNationality) – checked 2.1**

|               |          |                              |             |
|---------------|----------|------------------------------|-------------|
| **Field**     | **Type** | **Bytes**                    | **Note**    |
| Code          | Int32    | 4                            | Code        |
| Nationality   | String   | \-                           | Nationality |
| Packet length | bytes    | <span id="anchor-20"></span> |             |

>  **Catch condition (FCondition) – checked 2.1**

|               |          |                              |                 |
|---------------|----------|------------------------------|-----------------|
| **Field**     | **Type** | **Bytes**                    | **Note**        |
| PresentBg     | string   | \-                           | Bg description  |
| PresentEng    | String   | \-                           | Eng description |
| Code          | Int32    | 4                            | Code            |
| CodeAlpha     | String   | \-                           | Code Alpha 3    |
| Favorite      | Byte     | 1                            | 1 -default      |
| Packet length | bytes    | <span id="anchor-21"></span> |                 |

>  **Currency (FCurrency) – checked 2.1**

|               |          |                              |                 |
|---------------|----------|------------------------------|-----------------|
| **Field**     | **Type** | **Bytes**                    | **Note**        |
| CurrencyBg    | string   | \-                           | Bg description  |
| CurrencyEng   | String   | \-                           | Eng description |
| Code          | Int32    | 4                            | Code            |
| CodeStr       | String   | \-                           | Code Alpha 3    |
| Country       | String   | \-                           | Country         |
| Number        | Int32    | 4                            | Number          |
| Favorite      | Byte     | 1                            | 1 -default      |
| Packet length | bytes    | <span id="anchor-22"></span> |                 |

>  **Discarded species type (FDiscardedType) – checked 2.1**

|               |          |                              |                 |
|---------------|----------|------------------------------|-----------------|
| **Field**     | **Type** | **Bytes**                    | **Note**        |
| DiscardedBg   | String   | \-                           | Bg Description  |
| DiscardedEn   | String   | \-                           | Eng description |
| Code          | Byte     | 1                            | Code            |
| Favorite      | Byte     | 1                            | 1 - default     |
| Packet length | bytes    | <span id="anchor-23"></span> |                 |

>  **Fishing gear description (FGDescription) – checked 2.1**

|               |          |                              |                 |
|---------------|----------|------------------------------|-----------------|
| **Field**     | **Type** | **Bytes**                    | **Note**        |
| FGType        | string   | \-                           | Fg type         |
| FGCode        | String   | \-                           | Fg code         |
| FGSize        | String   | \-                           | Fg size         |
| FGUsingCount  | String   | \-                           | Used fg         |
| Seq           | Int32    | 4                            | Sequence number |
| Packet length | bytes    | <span id="anchor-24"></span> |                 |

>  **Fishing gear model (FGModel) – checked 2.1**

|               |          |                              |                 |
|---------------|----------|------------------------------|-----------------|
| **Field**     | **Type** | **Bytes**                    | **Note**        |
| ModelBg       | String   | \-                           | Bg Description  |
| ModelEn       | String   | \-                           | Eng description |
| Code          | Uint32   | 4                            | Code            |
| Packet length | bytes    | <span id="anchor-25"></span> |                 |

>  **Trademark of fishing gear (FGTrademark) – checked 2.1**

|               |          |                              |                 |
|---------------|----------|------------------------------|-----------------|
| **Field**     | **Type** | **Bytes**                    | **Note**        |
| TrademarklBg  | String   | \-                           | Bg Description  |
| TrademarkEn   | String   | \-                           | Eng description |
| Code          | Uint32   | 4                            | Code            |
| Packet length | bytes    | <span id="anchor-26"></span> |                 |

>  **Fishing activities (FishingActivity) – checked 2.1**

|               |          |                              |                                  |
|---------------|----------|------------------------------|----------------------------------|
| **Field**     | **Type** | **Bytes**                    | **Note**                         |
| Activity      | String   | \-                           | Bg Fishing activity description  |
| ActivityEng   | String   | \-                           | En Fishing activity description  |
| Code          | Int32    | 4                            | Code                             |
| Favorite      | Byte     | 1                            | 1 - default                      |
| Packet length | bytes    | <span id="anchor-27"></span> |                                  |

>  **Catch presentation (FPresentation) – checked 2.1**

|               |          |                              |                  |
|---------------|----------|------------------------------|------------------|
| **Field**     | **Type** | **Bytes**                    | **Note**         |
| PresentBg     | String   | \-                           | Bg description   |
| PresentEng    | String   | \-                           | En description   |
| PresentCode   | String   | \-                           | Code description |
| Favorite      | Byte     | 1                            | 1 - default      |
| Packet length | bytes    | <span id="anchor-28"></span> |                  |

>  **Catch quantity type (FQuantityType) – checked 2.1**

|               |          |                              |                 |
|---------------|----------|------------------------------|-----------------|
| **Field**     | **Type** | **Bytes**                    | **Note**        |
| QTypeBg       | String   | \-                           | Bg description  |
| QTypeEng      | String   | \-                           | En description  |
| Code          | Int32    | \-                           | Code            |
| Favorite      | Byte     | 1                            | 1 - default     |
| Packet length | bytes    | <span id="anchor-29"></span> |                 |

>  **Inspector identification type (InsIdTypeRec) – checked
            > 2.1**

|               |          |                              |                 |
|---------------|----------|------------------------------|-----------------|
| **Field**     | **Type** | **Bytes**                    | **Note**        |
| Code          | Int32    | 4                            | Code            |
| TypeBg        | String   | \-                           | Bg description  |
| TypeEng       | String   | \-                           | Eng description |
| Packet length | bytes    | <span id="anchor-30"></span> |                 |

>  **Port of landing description (LPorts) – checked 2.1**

|               |          |                              |                      |
|---------------|----------|------------------------------|----------------------|
| **Field**     | **Type** | **Bytes**                    | **Note**             |
| PortNameBg    | String   | \-                           | Bg name              |
| PortName      | String   | \-                           | Eng name             |
| Country       | String   | \-                           | Country description  |
| Code          | String   | \-                           | Code                 |
| IsHome        | Byte     | 1                            | Home port            |
| IsReg         | Byte     | 1                            | Port of registration |
| Packet length | bytes    | <span id="anchor-31"></span> |                      |

1.  **Packet description**

    1.  **Standard acknowledge packet (SAckP)**

|             |           |           |                                                   |
|-------------|-----------|-----------|---------------------------------------------------|
| **Field**   | **Bytes** | **Value** | **Note**                                          |
| STX         | 1         | 0x02      | Start of transmission                             |
| Sequence    | 1         | 0xFF      |                                                   |
| Description | 1         | 0x01      | Description of frame                              |
| Data        | n         | 0x24      | Ack for received packet with sequence number 0x24 |
| CRC         | 2         |           | Checksum (from Sequence to Data included)         |
| ETX         | 1         | 0x03      | End of transmission                               |

1.  1.  **Start fishing trip packet - 0x30 **

-   Direction M to S - Mobile to Server

|             |           |           |                                           |
|-------------|-----------|-----------|-------------------------------------------|
| **Field**   | **Bytes** | **Value** | **Note**                                  |
| STX         | 1         | 0x02      | Start of transmission                     |
| Sequence    | 1         | 0x22      | For example sequence number 0x22          |
| Description | 1         | 0x30      | Description of frame                      |
| Data        | n         | SFTRec    | Start fishing trip record                 |
| CRC         | 2         |           | Checksum (from Sequence to Data included) |
| ETX         | 1         | 0x03      | End of transmission                       |

>  **Response Ack **

-   Direction S to M - Server to Mobile

<table>
<tbody>
<tr class="odd">
<td><strong>Field</strong></td>
<td><strong>Bytes</strong></td>
<td><strong>Value</strong></td>
<td><strong>Note</strong></td>
</tr>
<tr class="even">
<td>STX</td>
<td>1</td>
<td>0x02</td>
<td>Start of transmission</td>
</tr>
<tr class="odd">
<td>Sequence</td>
<td>1</td>
<td>0xFF</td>
<td> </td>
</tr>
<tr class="even">
<td>Description</td>
<td>1</td>
<td>0x01</td>
<td>Description of frame</td>
</tr>
<tr class="odd">
<td>Data</td>
<td>1 + 10</td>
<td>0x22 and char(10)</td>
<td><p>Sequence number of acknowledged packet </p>
<p>If packet is start fishing operation 0x30 </p>
<p>and trip number (char(10))</p></td>
</tr>
<tr class="even">
<td>CRC</td>
<td>2</td>
<td></td>
<td>Checksum (from Sequence to Data included)</td>
</tr>
<tr class="odd">
<td>ETX</td>
<td>1</td>
<td>0x03</td>
<td>End of transmission</td>
</tr>
</tbody>
</table>

1.  1.  **End fishing trip packet - 0x31**

-   Direction M to S, See 1.2.

|             |           |           |                                           |
|-------------|-----------|-----------|-------------------------------------------|
| **Field**   | **Bytes** | **Value** | **Note**                                  |
| STX         | 1         | 0x02      | Start of transmission                     |
| Sequence    | 1         | 0x23      | For example sequence number 0x23          |
| Description | 1         | 0x31      | Description of frame                      |
| Data        | n         | EFTRec    | End fishing trip record                   |
| CRC         | 2         |           | Checksum (from Sequence to Data included) |
| ETX         | 1         | 0x03      | End of transmission                       |

>  **Response Ack**

-   Standard acknowledge packet
-   Direction S to M, See 1.4.1.

1.  1.  **Report fishing trip packet – 0x25**

-   Direction M to S, See 1.2.

|             |           |           |                                           |
|-------------|-----------|-----------|-------------------------------------------|
| **Field**   | **Bytes** | **Value** | **Note**                                  |
| STX         | 1         | 0x02      | Start of transmission                     |
| Sequence    | 1         | 0x24      | For example sequence number 0x24          |
| Description | 1         | 0x25      | Description of frame                      |
| Data        | n         | FTRec     | Full fishing trip data                    |
| CRC         | 2         |           | Checksum (from Sequence to Data included) |
| ETX         | 1         | 0x03      | End of transmission                       |

>  **Response Ack **

-   Standard acknowledge packet
-   Direction S to M, See 1.4.1.

1.  1.  **Get current fishing trip command packet – 0x15**

-   Direction S to M, See 1.2.

|             |           |           |                                           |
|-------------|-----------|-----------|-------------------------------------------|
| **Field**   | **Bytes** | **Value** | **Note**                                  |
| STX         | 1         | 0x02      | Start of transmission                     |
| Sequence    | 1         | 0x04      | For example sequence number 0x04          |
| Description | 1         | 0x25      | Description of frame                      |
| Data        | 0         | \-        | \-                                        |
| CRC         | 2         |           | Checksum (from Sequence to Data included) |
| ETX         | 1         | 0x03      | End of transmission                       |

>  **Response **

-   Direction M to S, See 1.4.4

1.  1.  **Report Fishing Day Information packet / 24 hour report packet
        > **

-   Direction M to S, See 1.3.8.

|             |           |           |                                           |
|-------------|-----------|-----------|-------------------------------------------|
| **Field**   | **Bytes** | **Value** | **Note**                                  |
| STX         | 1         | 0x02      | Start of transmission                     |
| Sequence    | 1         | 0x1A      | For example sequence number 0x1A          |
| Description | 1         | 0x26      | Description of frame                      |
| Data        | n         | FTRec     | Full fishing trip data                    |
| CRC         | 2         |           | Checksum (from Sequence to Data included) |
| ETX         | 1         | 0x03      | End of transmission                       |

>  **Response Ack **

-   Standard acknowledge packet
-   Direction S to M, See 1.4.1.

1.  1.  **Send Text Message packet – 0x06 **

-   Direction S to M, See 1.2.

|             |           |           |                                           |
|-------------|-----------|-----------|-------------------------------------------|
| **Field**   | **Bytes** | **Value** | **Note**                                  |
| STX         | 1         | 0x02      | Start of transmission                     |
| Sequence    | 1         | 0x11      | For example sequence number 0x11          |
| Description | 1         | 0x06      | Description of frame                      |
| Data        | n         | Byte\[\]  | Text message                              |
| CRC         | 2         |           | Checksum (from Sequence to Data included) |
| ETX         | 1         | 0x03      | End of transmission                       |

>  **Response Ack**

-   Standard acknowledge packet
-   Direction M to S, See 1.4.1.

1.  1.  **Send Text Message With GPS packet – 0x09 **

-   Direction M to S, See 1.2.

|             |           |           |                                           |
|-------------|-----------|-----------|-------------------------------------------|
| **Field**   | **Bytes** | **Value** | **Note**                                  |
| STX         | 1         | 0x02      | Start of transmission                     |
| Sequence    | 1         | 0x11      | For example sequence number 0x11          |
| Description | 1         | 0x09      | Description of frame                      |
| Data        | n         | TwGPSRec  | Text message with GPS, See 1.3.16.        |
| CRC         | 2         |           | Checksum (from Sequence to Data included) |
| ETX         | 1         | 0x03      | End of transmission                       |

1.  1.  **Response Ack**

-   Standard acknowledge packet
-   Direction S to M, See 1.4.1.

1.  1.  **Get Device Universal Time packet – 0x60**

-   Direction M to S, See 1.2

|             |           |           |                                           |
|-------------|-----------|-----------|-------------------------------------------|
| **Field**   | **Bytes** | **Value** | **Note**                                  |
| STX         | 1         | 0x02      | Start of transmission                     |
| Sequence    | 1         | 0x15      | For example sequence number 0x15          |
| Description | 1         | 0x60      | Description of frame                      |
| Data        | 0         | \-        | \-                                        |
| CRC         | 2         |           | Checksum (from Sequence to Data included) |
| ETX         | 1         | 0x03      | End of transmission                       |

-   Response

    -   See 1.4.10. Set Device Universal Time

1.  1.  **Set Device Universal Time packet – 0x61**

-   Direction S to M

|             |           |           |                                                            |
|-------------|-----------|-----------|------------------------------------------------------------|
| **Field**   | **Bytes** | **Value** | **Note**                                                   |
| STX         | 1         | 0x02      | Start of transmission                                      |
| Sequence    | 1         | 0x15      | For example sequence number 0x15                           |
| Description | 1         | 0x61      | Description of frame                                       |
| Data        | 4         | UTC       | Int32 - Timestamp since 2018.01.01 00:00:00 UTC in seconds |
| CRC         | 2         |           | Checksum (from Sequence to Data included)                  |
| ETX         | 1         | 0x03      | End of transmission                                        |

-   Response

    -   Direction M to S, See 1.4.1.

1.  1.  **Get meteo data **

-   Direction M to S, See 1.2.

|             |           |           |                                           |
|-------------|-----------|-----------|-------------------------------------------|
| **Field**   | **Bytes** | **Value** | **Note**                                  |
| STX         | 1         | 0x02      | Start of transmission                     |
| Sequence    | 1         | 0x15      | For example sequence number 0x15          |
| Description | 1         | 0x50      | Description of frame                      |
| Data        | 0         | \-        | \-                                        |
| CRC         | 2         |           | Checksum (from Sequence to Data included) |
| ETX         | 1         | 0x03      | End of transmission                       |

-   Response

    -   See 1.4.12. Meteo data packet

1.  1.  **Meteo data packet **

-   Direction S to M, See 1.2.

|             |           |           |                                           |
|-------------|-----------|-----------|-------------------------------------------|
| **Field**   | **Bytes** | **Value** | **Note**                                  |
| STX         | 1         | 0x02      | Start of transmission                     |
| Sequence    | 1         | 0x15      | For example sequence number 0x15          |
| Description | 1         | 0x51      | Description of frame                      |
| Data        | n         | MeteoDrec | Meteo data record, See 1.3.18.            |
| CRC         | 2         |           | Checksum (from Sequence to Data included) |
| ETX         | 1         | 0x03      | End of transmission                       |

>  **Response**

-   Standard acknowledge packet
-   Direction M to S, See 1.4.1

>   **Innovation Norway**

>   **Get IN Full Data**

-   Direction S to M

|             |           |           |                                            |
|-------------|-----------|-----------|--------------------------------------------|
| **Field**   | **Bytes** | **Value** | **Note**                                   |
| STX         | 1         | 0x02      | Start of transmission                      |
| Sequence    | 1         | 0x15      | For example sequence number 0x15           |
| Description | 1         | 0x80      | Description of frame/ Get IN Full Data cmd |
| Data        | 0         | \-        |                                            |
| CRC         | 2         |           | Checksum (from Sequence to Data included)  |
| ETX         | 1         | 0x03      | End of transmission                        |

>   **Response/ Auto report**

-   Direction M to S, See 1.4.1.

|             |           |                 |                                           |
|-------------|-----------|-----------------|-------------------------------------------|
| **Field**   | **Bytes** | **Value**       | **Note**                                  |
| STX         | 1         | 0x02            | Start of transmission                     |
| Sequence    | 1         | 0x15            | For example sequence number 0x15          |
| Description | 1         | 0x81            | Description of frame                      |
| Data        | n         | ASPSFullDataRec | ASPS IN Full Data                         |
| CRC         | 2         |                 | Checksum (from Sequence to Data included) |
| ETX         | 1         | 0x03            | End of transmission                       |

>  **Get IN ASPS Data **

-   Direction S to M

|             |           |           |                                           |
|-------------|-----------|-----------|-------------------------------------------|
| **Field**   | **Bytes** | **Value** | **Note**                                  |
| STX         | 1         | 0x02      | Start of transmission                     |
| Sequence    | 1         | 0x15      | For example sequence number 0x15          |
| Description | 1         | 0x82      | Description of frame / Get ASPS Data cmd  |
| Data        | 0         | \-        |                                           |
| CRC         | 2         |           | Checksum (from Sequence to Data included) |
| ETX         | 1         | 0x03      | End of transmission                       |

** **

>  **Response/ Auto report ASPSDataRec**

-   Direction M to S, See 1.4.2.

|             |           |             |                                           |
|-------------|-----------|-------------|-------------------------------------------|
| **Field**   | **Bytes** | **Value**   | **Note**                                  |
| STX         | 1         | 0x02        | Start of transmission                     |
| Sequence    | 1         | 0x15        | For example sequence number 0x15          |
| Description | 1         | 0x83        | Description of frame / Response           |
| Data        | n         | ASPSDataRec | ASPS Data                                 |
| CRC         | 2         |             | Checksum (from Sequence to Data included) |
| ETX         | 1         | 0x03        | End of transmission                       |

** **

>  **Get IN SSCB Data **

-    Direction S to M

|             |           |           |                                           |
|-------------|-----------|-----------|-------------------------------------------|
| **Field**   | **Bytes** | **Value** | **Note**                                  |
| STX         | 1         | 0x02      | Start of transmission                     |
| Sequence    | 1         | 0x15      | For example sequence number 0x15          |
| Description | 1         | 0x84      | Description of frame / Get SSCB Data cmd  |
| Data        | 0         | \-        |                                           |
| CRC         | 2         |           | Checksum (from Sequence to Data included) |
| ETX         | 1         | 0x03      | End of transmission                       |

>  **Response (SSCBDataRec)**

-   Direction M to S, See 1.4.3.

|             |           |             |                                           |
|-------------|-----------|-------------|-------------------------------------------|
| **Field**   | **Bytes** | **Value**   | **Note**                                  |
| STX         | 1         | 0x02        | Start of transmission                     |
| Sequence    | 1         | 0x15        | For example sequence number 0x15          |
| Description | 1         | 0x85        | Description of frame / Response           |
| Data        | n         | SSCBDataRec | SSCB Data                                 |
| CRC         | 2         |             | Checksum (from Sequence to Data included) |
| ETX         | 1         | 0x03        | End of transmission                       |

> **Get IN OECB Data**

-   Direction S to M

|             |           |           |                                           |
|-------------|-----------|-----------|-------------------------------------------|
| **Field**   | **Bytes** | **Value** | **Note**                                  |
| STX         | 1         | 0x02      | Start of transmission                     |
| Sequence    | 1         | 0x15      | For example sequence number 0x15          |
| Description | 1         | 0x86      | Description of frame / Get OECB Data cmd  |
| Data        | 0         | \-        |                                           |
| CRC         | 2         |           | Checksum (from Sequence to Data included) |
| ETX         | 1         | 0x03      | End of transmission                       |

> **Response**

-   Direction M to S, See 1.4.4.

|             |           |             |                                           |
|-------------|-----------|-------------|-------------------------------------------|
| **Field**   | **Bytes** | **Value**   | **Note**                                  |
| STX         | 1         | 0x02        | Start of transmission                     |
| Sequence    | 1         | 0x15        | For example sequence number 0x15          |
| Description | 1         | 0x85        | Description of frame / Response           |
| Data        | n         | OECBDataRec | OECB Data                                 |
| CRC         | 2         |             | Checksum (from Sequence to Data included) |
| ETX         | 1         | 0x03        | End of transmission                       |


pandoc -s -i index.html -t markdown |\
grep -v "^:" |\
grep -v '^```' |\
grep -v '<!-- -->' |\
sed -e ':again' -e N -e '$!b again' -e 's/{[^}]*}//g' \
>! index.md

