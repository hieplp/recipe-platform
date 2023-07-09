import React from "react";

export function CenterLayout({children, dataTheme}: { children: React.ReactNode, dataTheme?: string }) {
    return (
        <>
            <div className="flex justify-center items-center h-screen bg-gray-50 dark:bg-gray-900"
                 data-theme={dataTheme}>
                {children}
            </div>
        </>
    )
}