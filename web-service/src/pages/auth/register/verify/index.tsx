import {CenterLayout} from "~/components/layouts/CenterLayout";
import {BrandIcon} from "~/components/ui/Icon";
import React from "react";
import {useRouter} from "next/router";
import {VerifyEmailForm} from "~/components/forms/VerifyEmailForm";

export default function Register() {
    const router = useRouter();

    const goToLogin = () => {
        void router.push('/auth/login');
    }

    return (
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
                                xl:p-0
                               ">
                    <div className="p-6 space-y-4 md:space-y-6 sm:p-8">
                        <h1 className="text-xl font-bold leading-tight tracking-tight text-gray-900 md:text-2xl dark:text-white">
                            Verify email

                            <p className="text-sm text-gray-400">
                                Verify your email address to complete your account creation
                            </p>
                        </h1>
                        <div className="space-y-4 md:space-y-6 form-control">
                            <VerifyEmailForm/>
                        </div>
                    </div>
                </div>
            </div>
        </CenterLayout>
    )
}