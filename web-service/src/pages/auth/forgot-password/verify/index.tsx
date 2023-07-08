import {AuthLayout} from "~/pages/auth/AuthLayout";
import React from "react";
import {VerifyForgotPasswordForm} from "~/components/forms/auth/VerifyForgotPasswordForm";

export default function ForgotPassword() {
    return (
        <AuthLayout>
            <div className="text-xl leading-tight tracking-tight text-gray-900 md:text-2xl dark:text-white">
                <p className="font-bold">
                    Reset password
                </p>
                <p className="text-sm text-gray-400">
                    Enter your email address and we will send you a verification code
                </p>
            </div>
            <div className="space-y-4 md:space-y-6 form-control">
                <VerifyForgotPasswordForm/>
            </div>
        </AuthLayout>
    )
}