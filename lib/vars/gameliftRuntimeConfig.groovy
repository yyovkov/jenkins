// def call(Map config){
//     return "Hello world!"
// }

def call(Map conf) {
    // Options conf Map options required
    // * os: operatingSystem - [AMAZON_LINUX_2|WINDOWS_2012]
    // * config: UE5 build config - [Development|Shipping]
    // * concExe: concurent executions - Number
    if (conf.os == 'AMAZON_LINUX_2') {
        path = '/local/game/Mytaverse/Binaries/Linux/MytaverseServer'
        if(conf.config == 'Development') {
            return 'ServerProcesses=[{ \
                LaunchPath=' + path + '/MytaveseServer, \
                ConcurrentExecutions='+ conf.concExe + \
            '}]'
        }
        if(conf.config == 'Shipping') {
            ServerProcesses = [[
                'LaunchPath': path + '/MytaverseServer-Linux-Shipping',
                'ConcurrentExecutions': conf.concExe
            ]]
             
            return ServerProcesses
            // return 'ServerProcesses=[{ \
            //     LaunchPath=' + path + '/MytaverseServer-Linux-Shipping, \
            //     ConcurrentExecutions=' + conf.concExe + \
            // '}]'
        }
    } else {
        path = 'C:/game/Mytaverse/Binaries/Win64'
        if(conf.config == 'Development') {
            return 'ServerProcesses=[{ \
                LaunchPath=' + path + '/MytaverseServer.exe, \
                ConcurrentExecutions='+ conf.concExe + \
            '}]'
        }
        if(conf.config == 'Shipping') {
            return 'ServerProcesses=[{ \
                LaunchPath=' + path + '/MytaverseServer-Win64-Shipping.exe, \
                ConcurrentExecutions=' + conf.concExe + \
            '}]'
        }
    }
}
