[Setup]
AppName=EAFA PRIOR NOTIFICATION SYSTEM
AppVersion=1.0.0.5-alpha
DefaultDirName={pf}\eafa-pn-system
OutputBaseFilename=eafa-pn-system-setup
ArchitecturesInstallIn64BitMode=x64

[Dirs]
Name: "{app}\data"
Name: "{app}\logs"

[Files]
Source: "out\*"; DestDir: "{app}"; Flags: recursesubdirs

[Run]
Filename: "{app}\jre\bin\java.exe"; Parameters: "-jar ""{app}\tracker-server.jar"" --install .\conf\eafa-pn-system.xml"; Flags: runhidden

[UninstallRun]
Filename: "{app}\jre\bin\java.exe"; Parameters: "-jar ""{app}\tracker-server.jar"" --uninstall"; Flags: runhidden
