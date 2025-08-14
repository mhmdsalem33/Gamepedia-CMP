import SwiftUI
import ComposeApp

@main
struct iOSApp: App {


    init(){
    AppDiSetupKt.doInitKoin()
    LoggerSetupKt.initLogger()
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}