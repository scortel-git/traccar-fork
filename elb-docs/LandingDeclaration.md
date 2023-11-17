## Orbcomm пакет с добавен UniqueId

```02F440163031343432353330534B59383441376764725151696A48724547396D2D6D7434325875444101088038000000000022000000002104020000000000000119000000000010330B0B0000002A330B0B188C2700048A19000100840000000000000000001A0632312C31383B780503```
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
<td>UniqueID</td>
<td>String</td>
<td>15</td>
<td><p>uniqueID </p></td>
<td>01442530SKY84A7</td>
</tr>
<tr class="odd">
<td>TripnumberLenght</td>
<td>String</td>
<td>1</td>
<td><p>TripnumberLenght </p></td>
<td></td>
</tr>
<tr class="odd">
<td>Tripnumber</td>
<td>String</td>
<td>22</td>
<td><p>Tripnumber </p></td>
<td></td>
</tr>
<tr class="even">
<td>FORecsLenght</td>
<td>Byte</td>
<td>1</td>
<td>List of FORec </td>
<td></td>
</tr>

<tr class="even">
<td>FORecs</td>
<td>FORec[]</td>
<td>~</td>
<td>List of FORec </td>
<td></td>
</tr>

<tr class="even">
<td>FCRecsLenght</td>
<td>Byte</td>
<td>1</td>
<td>List of FCRec </td>
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