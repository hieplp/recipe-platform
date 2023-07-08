import React from "react";

export function CenterLayout({children}: { children: React.ReactNode }) {
    return (
        <>
            <div className="flex justify-center items-center h-screen bg-gray-50 dark:bg-gray-900">
                {children}
            </div>
        </>
    )
}