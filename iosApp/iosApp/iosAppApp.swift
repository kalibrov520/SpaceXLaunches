//
//  iosAppApp.swift
//  iosApp
//
//  Created by Ilya Kalibrov on 6/17/22.
//

import SwiftUI
import shared

@main
struct iOSApp: App {
    let sdk = SpaceXSDK(databaseDriverFactory: DatabaseDriverFactory())
    var body: some Scene {
        WindowGroup {
            ContentView(viewModel: .init(sdk: sdk))
        }
    }
}
