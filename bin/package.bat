@echo off
rem /**
rem  * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
rem  *
rem  * Author: ThinkGem@163.com
rem  */
echo.
echo [��Ϣ] ������̣�����war���ļ���
echo.
pause
echo.

cd %~dp0
cd..

call mvn clean package -Dmaven.test.skip=true

cd bin
pause