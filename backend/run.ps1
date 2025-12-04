$ErrorActionPreference = "Stop"

Write-Host "========================================" -ForegroundColor Cyan
Write-Host "Compilando y Ejecutando Backend" -ForegroundColor Cyan
Write-Host "========================================" -ForegroundColor Cyan
Write-Host ""

# Usar el Java de VSCode
$JAVA_HOME = "C:\Users\jhanc\.antigravity\extensions\redhat.java-1.50.0-win32-x64\jre\21.0.9-win32-x86_64"
$env:JAVA_HOME = $JAVA_HOME
$env:PATH = "$JAVA_HOME\bin;$env:PATH"

Write-Host "Usando Java:" -ForegroundColor Yellow
& java -version
Write-Host ""

Write-Host "Compilando proyecto..." -ForegroundColor Yellow
& .\mvnw.cmd clean compile

if ($LASTEXITCODE -eq 0) {
    Write-Host ""
    Write-Host "Compilacion exitosa! Ejecutando aplicacion..." -ForegroundColor Green
    Write-Host "La aplicacion estara disponible en: http://localhost:8080" -ForegroundColor Cyan
    Write-Host "Presiona Ctrl+C para detener" -ForegroundColor Yellow
    Write-Host ""
    & .\mvnw.cmd spring-boot:run
} else {
    Write-Host ""
    Write-Host "Error en la compilacion" -ForegroundColor Red
    Write-Host "Intenta ejecutar desde VSCode presionando F5 en BackendApplication.java" -ForegroundColor Yellow
}
