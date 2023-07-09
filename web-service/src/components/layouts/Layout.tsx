import React from "react";
import {Header} from "~/components/layouts/Header";
import {Footer} from "~/components/layouts/Footer";
import {NewsLetter} from "~/components/layouts/NewsLetter";

export function Layout({children}: { children: React.ReactNode }) {
    return (
        <>
            <div className="flex flex-col min-h-screen bg-gray-50 dark:bg-gray-900">
                <Header/>
                <main className="pt-28 w-full p-4 mx-auto flex-grow">
                    {children}
                </main>
                <NewsLetter/>
                <Footer/>
            </div>
        </>
    )
}