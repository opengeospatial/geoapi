set SAXONHOME=c:\javastuff\saxon_6.5.2
set GO1HOME=c:\go1
del umlxml.xml
java -jar %SAXONHOME%\saxon.jar -o umlxml.xml %1 %GO1HOME%\tools\newxmi2umlxml.xsl
java -jar %SAXONHOME%\saxon.jar -o umlxml.jml umlxml.xml %GO1HOME%\tools\newUMLSchema2Java.xsl
java -jar %SAXONHOME%\saxon.jar umlxml.jml %GO1HOME%\tools\Java_Files_Out.xsl

