
##### \[protocol\].address

Network interface for a the protocol. If not specified, server will bind
all interfaces.

##### \[protocol\].port

Port number for the protocol. Most protocols use TCP on the transport
layer. Some protocols use UDP. Some support both TCP and UDP.

##### \[protocol\].devices

List of devices for polling protocols. List should contain unique ids
separated by commas. Used only for polling protocols.

##### \[protocol\].interval

Polling interval in seconds. Used only for polling protocols.

##### \[protocol\].ssl

Enable SSL support for the protocol. Not all protocols support this.

##### \[protocol\].timeout

Connection timeout value in seconds. Because sometimes there is no way
to detect lost TCP connection old connections stay in open state. On
most systems there is a limit on number of open connection, so this
leads to problems with establishing new connections when number of
devices is high or devices data connections are unstable.

##### devicePassword

Device password. Commonly used in some protocol for sending commands.

##### \[protocol\].devicePassword

Device password. Commonly used in some protocol for sending commands.

##### \[protocol\].mask

Default protocol mask to use. Currently used only by Skypatrol protocol.

##### \[protocol\].messageLength

Custom message length. Currently used only by H2 protocol for specifying
binary message length.

##### \[protocol\].extended

Enable extended functionality for the protocol. The reason it\'s
disabled by default is that not all devices support it.

##### \[protocol\].utf8

Decode string as UTF8 instead of ASCII. Only applicable for some
protocols.

##### \[protocol\].can

Enable CAN decoding for the protocol. Similar to \'extended\'
configuration, it\'s not supported for some devices.

##### \[protocol\].ack

Indicates whether server acknowledgement is required. Only applicable
for some protocols.

##### \[protocol\].ignoreFixTime

Ignore device reported fix time. Useful in case some devices report
invalid time. Currently only available for GL200 protocol.

##### \[protocol\].decodeLow

Decode additional TK103 attributes. Not supported for some devices.

##### \[protocol\].longDate

Use long date format for Atrack protocol.

##### \[protocol\].decimalFuel

Use decimal fuel value format for Atrack protocol.

##### \[protocol\].custom

Indicates additional custom attributes for Atrack protocol.

##### \[protocol\].form

Custom format string for Atrack protocol.

##### \[protocol\].config

Protocol configuration. Required for some devices for decoding incoming
data.

##### \[protocol\].alarmMap

Alarm mapping for Atrack protocol.

##### \[protocol\].prefix

Indicates whether TAIP protocol should have prefixes for messages.

##### \[protocol\].server

Some devices require server address confirmation. Use this parameter to
configure correct public address.

##### suntech.protocolType

Protocol type for Suntech.

##### suntech.hbm

Suntech HBM configuration value.

##### \[protocol\].includeAdc

Format includes ADC value.

##### \[protocol\].includeRpm

Format includes RPM value.

##### \[protocol\].includeTemp

Format includes temperature values.

##### \[protocol\].disableCommands

Disable commands for the protocol. Not all protocols support this
option.

##### \[protocol\].format

Protocol format. Used by protocols that have configurable message
format.

##### \[protocol\].dateFormat

Protocol date format. Used by protocols that have configurable date
format.

##### decoder.timezone

Device time zone. Most devices report UTC time, but in some cases
devices report local time, so this parameter needs to be configured for
the server to be able to decode the time correctly.

##### orbcomm.accessId

ORBCOMM API access id.

##### orbcomm.password

ORBCOMM API password.

##### \[protocol\].alternative

Use alternative format for the protocol of commands.

##### \[protocol\].language

Protocol format includes a language field.

##### server.timeout

Server wide connection timeout value in seconds. See protocol timeout
for more information.

##### server.instantAcknowledgement

Send device responses immediately before writing it in the database.

##### server.statistics

Address for uploading aggregated anonymous usage statistics. Uploaded
information is the same you can see on the statistics screen in the web
app. It does not include any sensitive (e.g. locations).

##### fuelDropThreshold

Fuel drop threshold value. When fuel level drops from one position to
another for more the value, an event is generated.

##### fuelIncreaseThreshold

Fuel increase threshold value. When fuel level increases from one
position to another for more the value, an event is generated.

##### speedLimit

Speed limit value in knots.

##### event.overspeed.thresholdMultiplier

Speed limit threshold multiplier. For example, if the speed limit is
100, but we only want to generate an event if the speed is higher than
105, this parameter can be set to 1.05. Default multiplier is 1.0.

##### event.overspeed.minimalDuration

Minimal over speed duration to trigger the event. Value in seconds.

##### event.overspeed.preferLowest

Relevant only for geofence speed limits. Use the lowest speed limit from
all geofences.

##### event.behavior.accelerationThreshold

Driver behavior acceleration threshold. Value is in meter per second
squared.

##### event.behavior.brakingThreshold

Driver behavior braking threshold. Value is in meter per second squared.

##### event.ignoreDuplicateAlerts

Do not generate alert event if same alert was present in last known
location.

##### event.motion.processInvalidPositions

If set to true, invalid positions will be considered for motion logic.

##### event.motion.speedThreshold

If the speed is above specified value, the object is considered to be in
motion. Default value is 0.01 knots.

##### geofence.polylineDistance

Global polyline geofence distance. Within that distance from the
polyline, point is considered within the geofence. Each individual
geofence can also has \'polylineDistance\' attribute which will take
precedence.

##### database.memory

Enable in-memory database instead of an SQL database.

##### database.driverFile

Path to the database driver JAR file. System includes drivers for
MySQL, PostgreSQL and H2 databases. If you use one of those, you don\'t
need to specify this parameter.

##### database.driver

Database driver Java class. For H2 use \'org.h2.Driver\'. MySQL driver
class name is \'com.mysql.jdbc.Driver\'.

##### database.url

Database connection URL. By default System uses H2 database.

##### database.user

Database user name. Default administrator user for H2 database is
\'sa\'.

##### database.password

Database user password. Default password for H2 admin (sa) user is
empty.

##### database.changelog

Path to Liquibase master changelog file.

##### database.maxPoolSize

Database connection pool size. Default value is defined by the HikariCP
library.

##### database.checkConnection

SQL query to check connection status. Default value is \'SELECT 1\'. For
Oracle database you can use \'SELECT 1 FROM DUAL\'.

##### database.saveOriginal

Store original HEX or string data as \"raw\" attribute in the
corresponding position.

##### database.throttleUnknown

Throttle unknown device database queries when it sends repeated
requests.

##### database.ignoreUnknown

By default, server syncs with the database if it encounters and unknown
device. This flag allows to disable that behavior to improve performance
in some cases.

##### database.registerUnknown

Automatically register unknown devices in the database.

##### database.registerUnknown.defaultCategory

Default category for auto-registered devices.

##### database.registerUnknown.defaultGroupId

The group id assigned to auto-registered devices.

##### database.registerUnknown.regex

Automatically register unknown devices with regex filter.

##### database.saveEmpty

Store empty messages as positions. For example, heartbeats.

##### users.defaultDeviceLimit

Device limit for self registered users. Default value is -1, which
indicates no limit.

##### users.defaultExpirationDays

Default user expiration for self registered users. Value is in days. By
default no expiration is set.

##### ldap.url

LDAP server URL. For more info check [LDAP
config](https://www.System.org/ldap/).

##### ldap.user

LDAP server login.

##### ldap.password

LDAP server password.

##### ldap.force

Force LDAP authentication.

##### ldap.base

LDAP user search base.

##### ldap.idAttribute

LDAP attribute used as user id. Default value is \'uid\'.

##### ldap.nameAttribute

LDAP attribute used as user name. Default value is \'cn\'.

##### ldap.mailAttribute

LDAP attribute used as user email. Default value is \'mail\'.

##### ldap.searchFilter

LDAP custom search filter. If not specified, \'(=:login)\'
will be used as a filter.

##### ldap.adminFilter

LDAP custom admin search filter.

##### ldap.adminGroup

LDAP admin user group. Used if custom admin filter is not specified.

##### openid.force

Force OpenID Connect authentication. When enabled, the System login
page will be skipped and users are redirected to the OpenID Connect
provider.

##### openid.clientId

OpenID Connect Client ID. This is a unique ID assigned to each
application you register with your identity provider. Required to enable
SSO.

##### openid.clientSecret

OpenID Connect Client Secret. This is a secret assigned to each
application you register with your identity provider. Required to enable
SSO.

##### openid.issuerUrl

OpenID Connect Issuer (Base) URL. This is used to automatically
configure the authorization, token and user info URLs if provided.

##### openid.authUrl

OpenID Connect Authorization URL. This can usually be found in the
documentation of your identity provider or by using the well-known
configuration endpoint, eg.
https://auth.example.com//.well-known/openid-configuration Required to
enable SSO if openid.issuerUrl is not set.

##### openid.tokenUrl

OpenID Connect Token URL. This can be found in the same ways at
openid.authUrl. Required to enable SSO if openid.issuerUrl is not set.

##### openid.userInfoUrl

OpenID Connect User Info URL. This can be found in the same ways at
openid.authUrl. Required to enable SSO if openid.issuerUrl is not set.

##### openid.allowGroup

OpenID Connect group to restrict access to. If this is not provided, all
OpenID users will have access to System. This option will only work if
your OpenID provider supports the groups scope.

##### openid.adminGroup

OpenID Connect group to grant admin access. If this is not provided, no
groups will be granted admin access. This option will only work if your
OpenID provider supports the groups scope.

##### status.timeout

If no data is reported by a device for the given amount of time, status
changes from online to unknown. Value is in seconds. Default timeout is
10 minutes.

##### status.ignoreOffline

List of protocol names to ignore offline status. Can be useful to not
trigger status change when devices are configured to disconnect after
reporting a batch of data.

##### media.path

Path to the media folder. Server stores audio, video and photo files in
that folder. Sub-folders will be automatically created for each device
by unique id.

##### web.address

Optional parameter to specify network interface for web interface to
bind to. By default server will bind to all available interfaces.

##### web.port

Web interface TCP port number. By default System uses port 1914. To
avoid specifying port in the browser you can set it to 80 (default HTTP
port).

##### web.maxRequestsPerSec

Maximum API requests per second. Above this limit requests and delayed
and throttled.

##### web.maxRequestSec

Maximum API request duration in seconds.

##### web.sanitize

Sanitize all strings returned via API. This is needed to fix XSS issues
in the old web interface. New React-based interface doesn\'t require
this.

##### web.path

Path to the web app folder.

##### web.override

Path to a folder with overrides. It can be used for branding to keep
custom logos in a separate place.

##### web.timeout

WebSocket connection timeout in milliseconds. Default timeout is 5
minutes.

##### web.sessionTimeout

Authentication sessions timeout in seconds. By default no timeout.

##### web.console

Enable database access console via \'/console\' URL. Use only for
debugging. Never use in production.

##### web.debug

Server debug version of the web app. Not recommended to use for
performance reasons. It is intended to be used for development and
debugging purposes.

##### web.serviceAccountToken

A token to login as a virtual admin account. Can be used to restore
access in case of issues with regular admin login. For example, if
password is lost and can\'t be restored.

##### web.origin

Cross-origin resource sharing origin header value.

##### web.cacheControl

Cache control header value. By default resources are cached for one
hour.

##### server.forward

Host for raw data forwarding.

##### forward.type

Position forwarding format. Available options are \"url\", \"json\" and
\"kafka\". Default is \"url\".

##### forward.exchange

Position forwarding AMQP exchange.

##### forward.topic

Position forwarding Kafka topic or AQMP Routing Key.

##### forward.url

URL to forward positions. Data is passed through URL parameters. For
example,  for device identifier,  and
for coordinates.

##### forward.header

Additional HTTP header, can be used for authorization.

##### forward.retry.enable

Position forwarding retrying enable. When enabled, additional attempts
are made to deliver positions. If initial delivery fails, because of an
unreachable server or an HTTP response different from \'2xx\', the
software waits for \'forward.retry.delay\' milliseconds to retry
delivery. On subsequent failures, this delay is duplicated. If
forwarding is retried for \'forward.retry.count\', retrying is canceled
and the position is dropped. Positions pending to be delivered are
limited to \'forward.retry.limit\'. If this limit is reached, positions
get discarded.

##### forward.retry.delay

Position forwarding retry first delay in milliseconds. Can be set to
anything greater than 0. Defaults to 100 milliseconds.

##### forward.retry.count

Position forwarding retry maximum retries. Can be set to anything
greater than 0. Defaults to 10 retries.

##### forward.retry.limit

Position forwarding retry pending positions limit. Can be set to
anything greater than 0. Defaults to 100 positions.

##### event.forward.type

Events forwarding format. Available options are \"json\" and \"kafka\".
Default is \"json\".

##### event.forward.exchange

Events forwarding AMQP exchange.

##### event.forward.topic

Events forwarding Kafka topic or AQMP Routing Key.

##### event.forward.url

Events forwarding URL.

##### event.forward.header

Events forwarding headers. Example value: FirstHeader: hello
SecondHeader: world

##### templates.root

Root folder for all template files.

##### mail.debug

Log emails instead of sending them via SMTP. Intended for testing
purposes only.

##### mail.smtp.systemOnly

Restrict global SMTP configuration to system messages only (e.g.
password reset).

##### mail.smtp.ignoreUserConfig

Force SMTP settings from the config file and ignore user attributes.

##### mail.smtp.host

The SMTP server to connect to.

##### mail.smtp.port

The SMTP server port to connect. Defaults to 25.

##### mail.transport.protocol

Email transport protocol. Default value is \"smtp\".

##### mail.smtp.starttls.enable

If true, enables the use of the STARTTLS command (if supported by the
server) to switch the connection to a TLS-protected connection before
issuing any login commands.

##### mail.smtp.starttls.required

If true, requires the use of the STARTTLS command. If the server
doesn\'t support the STARTTLS command, or the command fails, the connect
method will fail.

##### mail.smtp.ssl.enable

If set to true, use SSL to connect and use the SSL port by default.

##### mail.smtp.ssl.trust

If set to \"\*\", all hosts are trusted. If set to a whitespace
separated list of hosts, those hosts are trusted. Otherwise, trust
depends on the certificate the server presents.

##### mail.smtp.ssl.protocols

Specifies the SSL protocols that will be enabled for SSL connections.

##### mail.smtp.username

SMTP connection username.

##### mail.smtp.password

SMTP connection password.

##### mail.smtp.from

Email address to use for SMTP MAIL command.

##### mail.overwrite.enable

Flag to enable ignition use for trips calculation.

##### mail.smtp.fromName

The personal name for the email from address.

##### sms.http.url

SMS API service full URL. Enables SMS commands and notifications.

##### sms.http.authorizationHeader

SMS API authorization header name. Default value is \'Authorization\'.

##### sms.http.authorization

SMS API authorization header value. This value takes precedence over
user and password.

##### sms.http.user

SMS API basic authentication user.

##### sms.http.password

SMS API basic authentication password.

##### sms.http.template

SMS API body template. Placeholders  and  can be used in
the template. If value starts with \'

AWS Access Key with SNS permission.

##### sms.aws.secret

AWS Secret Access Key with SNS permission.

##### sms.aws.region

AWS Region for SNS service. Make sure to use regions that are supported
for messaging.

##### notificator.types

Enabled notification options. Comma-separated string is expected.
Example: web,mail,sms

##### notificator.system.key

System notification API key.

##### notificator.firebase.serviceAccount

Firebase service account JSON.

##### notificator.pushover.user

Pushover notification user name.

##### notificator.pushover.token

Pushover notification user token.

##### notificator.telegram.key

Telegram notification API key.

##### notificator.telegram.chatId

Telegram notification chat id to post messages to.

##### notificator.telegram.sendLocation

Telegram notification send location message.

##### report.periodLimit

Maximum time period for reports in seconds. Can be useful to prevent
users to request unreasonably long reports. By default there is no
limit.

##### report.fastThreshold

Time threshold for fast reports. Fast reports are more efficient, but
less accurate and missing some information. The value is in seconds. One
day by default.

##### report.trip.minimalTripDistance

Trips less than minimal duration and minimal distance are ignored. 300
seconds and 500 meters are default.

##### report.trip.minimalTripDuration

Trips less than minimal duration and minimal distance are ignored. 300
seconds and 500 meters are default.

##### report.trip.minimalParkingDuration

Parking less than minimal duration does not cut trip. Default 300
seconds.

##### report.trip.minimalNoDataDuration

Gaps of more than specified time are counted as stops. Default value is
one hour.

##### report.trip.useIgnition

Flag to enable ignition use for trips calculation.

##### report.ignoreOdometer

Ignore odometer value reported by the device and use server-calculated
total distance instead. This is useful if device reports invalid or zero
odometer values.

##### filter.enable

Boolean flag to enable or disable position filtering.

##### filter.invalid

Filter invalid (valid field is set to false) positions.

##### filter.zero

Filter zero coordinates. Zero latitude and longitude are theoretically
valid values, but it practice it usually indicates invalid GPS data.

##### filter.duplicate

Filter duplicate records (duplicates are detected by time value).

##### filter.outdated

Filter messages that do not have GPS location. If they are not filtered,
they will include the last known location.

##### filter.future

Filter records with fix time in the future. The value is specified in
seconds. Records that have fix time more than the specified number of
seconds later than current server time would be filtered out.

##### filter.past

Filter records with fix time in the past. The value is specified in
seconds. Records that have fix time more than the specified number of
seconds before current server time would be filtered out.

##### filter.accuracy

Filter positions with accuracy less than specified value in meters.

##### filter.approximate

Filter cell and wifi locations that are coming from geolocation
provider.

##### filter.static

Filter positions with exactly zero speed values.

##### filter.distance

Filter records by distance. The values is specified in meters. If the
new position is less far than this value from the last one it gets
filtered out.

##### filter.maxSpeed

Filter records by Maximum Speed value in knots. Can be used to filter
jumps to far locations even if Position appears valid or if Position
\`speed\` field reported by the device is also within limits. Calculates
speed from the distance to the previous position and the elapsed time.
Tip: Shouldn\'t be too low. Start testing with values at about 25000.

##### filter.minPeriod

Filter position if time from previous position is less than specified
value in seconds.

##### filter.dailyLimit

Filter position if the daily limit is exceeded for the device.

##### filter.relative

If false, the server expects all locations to come sequentially (for
each device). Filter checks for duplicates, distance, speed, or time
period only against the location that was last received by server. If
true, the server expects locations to come at random order (since
tracking device might go offline). Filter checks for duplicates,
distance, speed, or time period against the preceding Position\'s.
Important: setting to true can cause potential performance issues.

##### filter.skipLimit

Time limit for the filtering in seconds. If the time difference between
the last position was received by server and a new position is received
by server is more than this limit, the new position will not be filtered
out.

##### filter.skipAttributes.enable

Enable attributes skipping. Attribute skipping can be enabled in the
config or device attributes. If position contains any attribute
mentioned in \"filter.skipAttributes\" config key, position is not
filtered out.

##### filter.skipAttributes

Attribute skipping can be enabled in the config or device attributes. If
position contains any attribute mentioned in \"filter.skipAttributes\"
config key, position is not filtered out.

##### time.override

Override device time. Possible values are \'deviceTime\' and
\'serverTime\'

##### protocols.enable

List of protocols to enable. If not specified, System enabled all
protocols that have port numbers listed. The value is a comma-separated
list of protocol names. Example value: teltonika,osmand

##### time.protocols

List of protocols for overriding time. If not specified override is
applied globally. List consist of protocol names that can be separated
by comma or single space character.

##### coordinates.filter

Replaces coordinates with last known if change is less than a
\'coordinates.minError\' meters or more than a \'coordinates.maxError\'
meters. Helps to avoid coordinates jumps during parking period or jumps
to zero coordinates.

##### coordinates.minError

Distance in meters. Distances below this value gets handled like
explained in \'coordinates.filter\'.

##### coordinates.maxError

Distance in meters. Distances above this value gets handled like
explained in \'coordinates.filter\', but only if Position is also marked
as \'invalid\'.

##### processing.remoteAddress.enable

Enable to save device IP addresses information. Disabled by default.

##### processing.copyAttributes.enable

Enable copying of missing attributes from last position to the current
one. Might be useful if device doesn\'t send some values in every
message.

##### processing.copyAttributes

List of attributes to copy. Attributes should be separated by a comma
without any spacing. For example: alarm,ignition

##### processing.computedAttributes.deviceAttributes

Include device attributes in the computed attribute context.

##### processing.computedAttributes.lastAttributes

Include last position attributes in the computed attribute context.

##### processing.computedAttributes.localVariables

Enable local variables declaration.

##### processing.computedAttributes.loops

Enable loops processing.

##### processing.computedAttributes.newInstanceCreation

Enable new instances creation. When disabled, parsing a
script/expression using \'new(\...)\' will throw a parsing exception;

##### geocoder.enable

Boolean flag to enable or disable reverse geocoder.

##### geocoder.type

Reverse geocoder type. Check reverse geocoding documentation for more
info. By default (if the value is not specified) server uses Google API.

##### geocoder.url

Geocoder server URL. Applicable only to Nominatim and Gisgraphy
providers.

##### geocoder.id

App id for use with Here provider.

##### geocoder.key

Provider API key. Most providers require API keys.

##### geocoder.language

Language parameter for providers that support localization (e.g. Google
and Nominatim).

##### geocoder.format

Address format string. Default value is %h %r, %t, %s, %c. See
AddressFormat for more info.

##### geocoder.cacheSize

Cache size for geocoding results.

##### geocoder.ignorePositions

Disable automatic reverse geocoding requests for all positions.

##### geocoder.processInvalidPositions

Boolean flag to apply reverse geocoding to invalid positions.

##### geocoder.reuseDistance

Optional parameter to specify minimum distance for new reverse geocoding
request. If distance is less than specified value (in meters), then
System will reuse last known address.

##### geocoder.onRequest

Perform geocoding when preparing reports and sending notifications.

##### geolocation.enable

Boolean flag to enable LBS location resolution. Some devices send cell
towers information and WiFi point when GPS location is not available.
System can determine coordinates based on that information using third
party services. Default value is false.

##### geolocation.type

Provider to use for LBS location. Available options: google, mozilla and
opencellid. By default opencellid is used. You have to supply a key that
you get from corresponding provider. For more information see LBS
geolocation documentation.

##### geolocation.url

Geolocation provider API URL address. Not required for most providers.

##### geolocation.key

Provider API key. OpenCellID service requires API key.

##### geolocation.processInvalidPositions

Boolean flag to apply geolocation to invalid positions.

##### geolocation.reuse

Reuse last geolocation result if network details have not changed.

##### geolocation.requireWifi

Process geolocation only when Wi-Fi information is available. This makes
the result more accurate.

##### geolocation.mcc

Default MCC value to use if device doesn\'t report MCC.

##### geolocation.mnc

Default MNC value to use if device doesn\'t report MNC.

##### speedLimit.enable

Boolean flag to enable speed limit API to get speed limit values
depending on location. Default value is false.

##### speedLimit.type

Provider to use for speed limit. Available options: overpass. By default
overpass is used.

##### speedLimit.url

Speed limit provider API URL address.

##### speedLimit.accuracy

Search radius for speed limit. Value is in meters. Default value is 100.

##### location.latitudeHemisphere

Override latitude sign / hemisphere. Useful in cases where value is
incorrect because of device bug. Value can be N for North or S for
South.

##### location.longitudeHemisphere

Override longitude sign / hemisphere. Useful in cases where value is
incorrect because of device bug. Value can be E for East or W for West.

##### web.requestLog.path

Jetty Request Log Path. The path must include the string \"yyyy_mm_dd\",
which is replaced with the actual date when creating and rolling over
the file. Example: ./logs/jetty-yyyy_mm_dd.request.log

##### web.requestLog.retainDays

Set the number of days before rotated request log files are deleted.

##### web.disableHealthCheck

Disable systemd health checks.

##### web.sameSiteCookie

Sets SameSite cookie attribute value. Supported options: Lax, Strict,
None.

##### web.persistSession

Enables persisting Jetty session to the database

##### web.url

Public URL for the web app. Used for notification, report link and
OpenID Connect. If not provided, System will attempt to get a URL from
the server IP address, but it might be a local address.

##### logger.console

Output logging to the standard terminal output instead of a log file.

##### logger.queries

Log executed SQL queries.

##### logger.file

Log file name. For rotating logs, a date is added at the end of the file
name for non-current logs.

##### logger.level

Logging level. Default value is \'info\'. Available options: off,
severe, warning, info, config, fine, finer, finest, all.

##### logger.fullStackTraces

Print full exception traces. Useful for debugging. By default shortened
traces are logged.

##### logger.rotate

Create a new log file daily. Helps with log management. For example,
downloading and cleaning logs. Enabled by default.

##### logger.rotate.interval

Log file rotation interval, the default rotation interval is once a day.
This option is ignored if \'logger.rotate\' = false Available options:
day, hour

##### logger.attributes

A list of position attributes to log.

##### broadcast.type

Broadcast method. Available options are \"multicast\" and \"redis\". By
default (if the value is not specified or does not matches available
options) server disables broadcast.

##### broadcast.interface

Multicast interface. It can be either an IP address or an interface
name.

##### broadcast.address

Multicast address or Redis URL for broadcasting synchronization events.

##### broadcast.port

Multicast port for broadcasting synchronization events.
