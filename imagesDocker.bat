@echo off
setlocal

REM Caminho da pasta pai
set BASE_DIR=C:\DEV\workspaces\ingress

echo ==========================================
echo Iniciando build das imagens Docker
echo ==========================================

for /D %%D in ("%BASE_DIR%\*") do (
    echo.
    echo ==========================================
    echo Processando %%~nxD
    echo ==========================================

    pushd "%%D"

    if exist pom.xml (
        call mvn compile jib:dockerBuild

        if errorlevel 1 (
            echo.
            echo ERRO ao construir %%~nxD
            popd
            pause
            exit /b 1
        )
    ) else (
        echo pom.xml nao encontrado. Ignorando...
    )

    popd
)

echo.
echo ==========================================
echo Todos os microsservicos foram processados!
echo ==========================================

pause