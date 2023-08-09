def call(body) {
    powershell '''
        Write-Host === Set DefaultGame.ini (set Maps) ===
        $defaultGameConfigModifyHelper = Join-Path Devops cli_tools `
          | Join-Path -ChildPath config_modify `
          | Join-Path -ChildPath DefaultGameConfigModifyHelper.exe
        $fileDefaultGameIni = Join-Path Config DefaultGame.ini
        $dirMaps = Join-Path Devops Maps
        # Execute command
        & ${defaultGameConfigModifyHelper} `
            ${fileDefaultGameIni} `
            ${dirMaps} `
            ${env:MAPS_CONFIG_NAME}
            
        Write-Host === Set DefaultEngine.ini (set Sentry) ===
        $fileDefaultEngineIni = Join-Path Config DefaultEngine.ini
        $sentry = Get-IniContent ${fileDefaultEngineIni}
        $sentry["/Script/Sentry.SentrySettings"]["InitAutomatically"]='True'
        $sentry["/Script/Sentry.SentrySettings"]["DsnUrl"] = ${env:SENTRY_URL}
        $sentry | Out-IniFile -FilePath ${fileDefaultEngineIni} -Force
        Write-Host InitAutomatically: $sentry["/Script/Sentry.SentrySettings"]["InitAutomatically"]
        Write-Host DsnUrl: $sentry["/Script/Sentry.SentrySettings"]["DsnUrl"]

        Write-Host === Set DefaultGame.ini ===
        $fileDefaultGameIni = Join-Path Config DefaultGame.ini
        $ini= Get-IniContent $fileDefaultGameIni
        $ini["/Script/Mytaverse.MVDeveloperSettings"]["TargetConnection"] = ${env:TARGET_CONNECTION}
        $ini["/Script/Mytaverse.MVDeveloperSettings"]["TargetPlatform"] = "CP_Computer"
        $ini["/Script/WebViewEnhanced.WebViewEnhancedDeveloperSettings"]["NameUser"] = ${env:CRED_CICD_USR}
        $ini["/Script/WebViewEnhanced.WebViewEnhancedDeveloperSettings"]["PasswordUser"] = ${env:CRED_CICD_PSW}
        $ini | Out-IniFile -FilePath ${fileDefaultGameIni} -Force
        Write-Host File: ${fileDefaultGameIni} updated

        Write-Host === Build Server ===
        $cmdBuildServer =   "& `"${env:UAT_PATH}`" BuildCookRun" + `
                            " -project=`"${env:PROJECT_PATH}`"" + `
                            " -platform=`"${env:PLATFORM}`"" + `
                            " -serverconfig=`"${env:SERVER_CONFIG}`"" + `
                            " -archivedirectory=`"${env:PACKAGE_PATH}/${env:BUILD_NUMBER}`"" + `
                            " ${env:SERVER_BUILD_FLAGS}" + `
                            " -noturnkeyvariables"
        Write-Host Build Server Command: $cmdBuildServer
        Invoke-Expression $cmdBuildServer
    '''
}
