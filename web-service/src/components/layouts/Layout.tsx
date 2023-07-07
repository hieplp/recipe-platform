import React from "react";
import {Header} from "~/components/layouts/Header";

export function Layout({children}: { children: React.ReactNode }) {
    return (
        <>
            <Header/>
            <main className="pt-28 w-full p-4 mx-auto" data-theme="mytheme">
                {children}
            </main>
        </>
    )
}