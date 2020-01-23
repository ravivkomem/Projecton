@echo off

set mypath=%cd%
set mysqlPath="C:\Program Files\MySQL\MySQL Server 8.0\bin"

REM :::::::::::::: Map all project folder to R :::::::::::::
echo Mapping project folder to R:\ drive
if exist R:\ subst R: /D
subst R: "%mypath%"

REM :::::::::::::: Create icm scheme ::::::::::::::::::::::::
echo Creating icm scheme in mysql
pushd %mysqlPath%
Start /b /wait mysql.exe --protocol=tcp --host=127.0.0.1 --user=root --password=Aa123456 --port=3306 --default-character-set=utf8 --comments < "R:\create_schema.sql"

REM :::::::::::::: Importing icm scheme ::::::::::::::::::::::::
echo Importing icm data
pushd %mysqlPath%
Start /b /wait mysql.exe --protocol=tcp --host=127.0.0.1 --user=root --password=Aa123456 --port=3306 --default-character-set=utf8 --comments --database=icm  < "R:\icm_database.sql"

REM ::::::::::::: Running jarfix :::::::::::::::::::::::::::::::
rem echo running jarfix
rem pushd %mypath%
rem Start /b /wait jarfix.exe

REM subst R: /D

pause