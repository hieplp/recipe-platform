import React from "react";
import {VerifyEmailForm} from "~/components/forms/auth/VerifyEmailForm";
import {AuthLayout} from "~/pages/auth/AuthLayout";

export default function Register() {
    return (
        <AuthLayout>
            <div className="text-xl leading-tight tracking-tight text-gray-900 md:text-2xl dark:text-white">
                <p className="font-bold">
                    Verify email
                </p>
                <p className="text-sm text-gray-400">
                    Verify your email address to complete your account creation
                </p>
            </div>
            <div className="space-y-4 md:space-y-6 form-control">
                <VerifyEmailForm/>
            </div>
        </AuthLayout>
    )
}