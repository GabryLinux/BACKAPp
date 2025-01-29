# Script PowerShell: CreateCalcScheduledTask.ps1


############################################################
####################### IMPORTANTE!! #######################
######### SPECIFICARE SEMPRE I PERCORSI ASSOLUTI ###########
############################################################
param(
    [string]$scriptFile = "C:\Users\Gabry\Desktop\projects\BACKAPp\app\src\main\java\resources\copyScript.ps1", # Nome del task
    [string]$fileToCopy = "file.txt",       # Nome del file da copiare
    [string]$StartTime,  # Ora di esecuzione (formato HH:mm)
    [string]$srcPath, # Percorso sorgente
    [string]$dstPath # Percorso destinazione
)

if(-not $StartTime -or -not $srcPath -or -not $dstPath) {
    Write-Host "Errore: Assicurati di specificare l'ora di esecuzione del task, il percorso sorgente e destinazione della copia." -ForegroundColor Red
    exit 1
}

# Configura l'azione del task (apre la calcolatrice di Windows)
$action = New-ScheduledTaskAction -Execute "powershell.exe" -Argument "-file $scriptFile -SourcePath $srcPath -DestinationPath $dstPath"

# Configura il trigger per eseguire il task quotidianamente all'ora specificata
$trigger = New-ScheduledTaskTrigger -Daily -At $StartTime

# Configura le impostazioni del task (esegui anche se l'utente non è connesso)
$settings = New-ScheduledTaskSettingsSet -AllowStartIfOnBatteries -DontStopIfGoingOnBatteries -StartWhenAvailable

$currentUser = "$env:UserDomain\$env:UserName"
# Crea il task nel Task Scheduler di Windows
if(-not (Get-ScheduledTask -TaskName "RunBackupDB" -ErrorAction SilentlyContinue)) {
    Register-ScheduledTask -TaskName "RunBackupDB" -Action $action -Trigger $trigger -User $currentUser -RunLevel Limited -Settings $settings
} else {
    Set-ScheduledTask -TaskName "RunBackupDB" -Action $action -Trigger $trigger
    exit 1
}
#Register-ScheduledTask -TaskName "RunBackupDB" -Action $action -Trigger $trigger -User $currentUser -RunLevel Limited

# Conferma la creazione del task
Write-Host "Task pianificato creato con successo!" -ForegroundColor Green
