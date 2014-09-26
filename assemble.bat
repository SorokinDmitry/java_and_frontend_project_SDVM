@ECHO OFF

mvn compile assembly:single
echo assemble done
copy .\target\java_and_frontend_project_SDVM-1.0-jar-with-dependencies.jar .\
echo copy done
pause