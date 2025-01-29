# Nome del task da verificare
$taskName = "RunBackupDB"

try {
    # Verifica se il task esiste
    $task = Get-ScheduledTask -TaskName $taskName -ErrorAction Stop

    
    # Se il task esiste, eseguilo
    Write-Host "Il task '$taskName' esiste. Esecuzione in corso..." -ForegroundColor Green
    Start-ScheduledTask -TaskName $taskName

    # Conferma che l'esecuzione è stata avviata
    Write-Host "Il task '$taskName' è stato avviato correttamente." -ForegroundColor Green
} catch {
    # Se il task non esiste, genera un errore
    Write-Host "Errore: Il task '$taskName' non esiste nel Task Scheduler." -ForegroundColor Red
    throw "Task '$taskName' non trovato."
}