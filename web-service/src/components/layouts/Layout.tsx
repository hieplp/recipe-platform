import React from "react";
import {Header} from "~/components/layouts/Header";
import {Footer} from "~/components/layouts/Footer";
import {NewsLetter} from "~/components/layouts/NewsLetter";

export function Layout({children}: { children: React.ReactNode }) {
    return (
        <>
            <Header/>
            <main className="pt-28 w-full p-4 mx-auto">
                {children}
            </main>
            <NewsLetter/>
            <Footer/>
        </>
    )
}