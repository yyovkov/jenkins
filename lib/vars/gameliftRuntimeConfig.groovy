// def call(Map config){
//     return "Hello world!"
// }

def call(Map conf) {
    // Options conf Map options required
    // * os: operatingSystem - [AMAZON_LINUX_2|WINDOWS_2012]
    // * config: UE5 build config - [Development|Shipping]
    // * concExe: concurent executions - Number

    if (conf.os == 'AMAZON_LINUX_2') {
        path = '/local/game/Mytaverse/Binaries/Linux'
        if(conf.config == 'Development') {
           paht = path + '/MytaverseServer'
        }
        if(conf.config == 'Shipping') {
            path = path + '/MytaverseServer-Linux-Shipping'
        }
    } else {
        path = 'C:/game/Mytaverse/Binaries/Win64'

        if(conf.config == 'Development') {
            path = path + '/MytaverseServer.exe'
        }
        if(conf.config == 'Shipping') {
            path = path + '/MytaverseServer-Win64-Shipping.exe'
        }
    }


    ServerProcesses = [[
        'LaunchPath': path,
        'ConcurrentExecutions': conf.concExe
    ]]

    return ServerProcesses
}
