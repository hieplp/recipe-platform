import {CenterLayout} from "~/components/layouts/CenterLayout";
import {BrandIcon} from "~/components/ui/Icon";
import React from "react";

type AuthLayoutProps = {
    children: React.ReactNode
}

export function AuthLayout({children}: AuthLayoutProps) {
    return (
        <>
            <CenterLayout>
                <div className="w-full md:w-3/4
                                flex flex-col
                                items-center justify-center
                                px-6 py-8 mx-auto
                                md:h-screen lg:py-0">
                    <BrandIcon/>
                    <div className="w-full bg-white
                                    rounded-lg shadow
                                    mt-5 md:mt-5
                                    sm:max-w-md
                                    xl:p-0">
                        <div className="p-6 space-y-4 md:space-y-6 sm:p-8">
                            {children}
                        </div>
                    </div>
                </div>
            </CenterLayout>
        </>
    )
}