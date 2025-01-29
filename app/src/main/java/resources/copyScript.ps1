# Script PowerShell: CopyFileWithCurrentDateAndTime.ps1

param(
    [string]$SourcePath,    # Primo argomento: Percorso sorgente
    [string]$DestinationPath # Secondo argomento: Percorso destinazione
)
if(-not $SourcePath -or -not $DestinationPath) {
    Write-Host "Errore: Assicurati di specificare il percorso sorgente e di destinazione." -ForegroundColor Red
    exit 1
}

# Verifica che il file "file.txt" esista nel percorso sorgente
$sourceFile = Join-Path -Path $SourcePath -ChildPath "file.txt"
if (-not (Test-Path -Path $sourceFile)) {
    Write-Host "Errore: Il file 'file.txt' non esiste nel percorso sorgente '$SourcePath'" -ForegroundColor Red
    exit 1
}

# Ottieni la data e l'ora attuali (NOW)
$currentDateTime = Get-Date

# Formatta la data e l'ora come yyyyMMdd_HHmmss per il nome del file
$dateTimeFormatted = $currentDateTime.ToString("yyyyMMdd_HHmmss")

# Genera il nuovo nome del file con la data e l'ora attuali
$newFileName = "file$dateTimeFormatted.txt"

# Verifica che il percorso di destinazione esista, altrimenti crealo
if (-not (Test-Path -Path $DestinationPath)) {
    New-Item -ItemType Directory -Path $DestinationPath | Out-Null
}

# Percorso completo del file nella destinazione
$destinationFile = Join-Path -Path $DestinationPath -ChildPath $newFileName

# Copia il file nella destinazione con il nuovo nome
Copy-Item -Path $sourceFile -Destination $destinationFile

# Conferma l'operazione
Write-Host "File copiato con successo!" -ForegroundColor Green
Write-Host "Percorso sorgente: $sourceFile"
Write-Host "Percorso destinazione: $destinationFile"
