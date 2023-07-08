import React from "react";
import {useRouter} from "next/router";
import {PrimaryButton} from "~/components/ui/Button";

// --------------------------------------------------------------------------
// XXX RegisterForm
// --------------------------------------------------------------------------
export function RegisterForm() {
    //
    const router = useRouter();

    const register = () => {
        void router.push("/auth/register/verify");
    }

    return (
        <section className="form-control">
            {/*Username*/}
            <div className="w-full mb-5">
                <label className="label">
                    <span className="label-text">Username</span>
                </label>
                <input type="text"
                       placeholder="hieplp"
                       className="input input-md w-full input-bordered"/>
            </div>

            {/*Email*/}
            <div className="w-full mb-5">
                <label className="label">
                    <span className="label-text">Email</span>
                </label>
                <input type="email"
                       placeholder="hiepphuocly@gmail.com"
                       className="input input-md w-full input-bordered"/>
            </div>

            {/*Password*/}
            <div className="w-full mb-5">
                <label className="label">
                    <span className="label-text">Password</span>
                </label>
                <input type="password"
                       placeholder="••••••••"
                       className="input input-md w-full input-bordered"/>
            </div>

            {/*Confirm Password*/}
            <div className="w-full mb-5">
                <label className="label">
                    <span className="label-text">Confirm Password</span>
                </label>
                <input type="password"
                       placeholder="••••••••"
                       className="input input-md w-full input-bordered"/>
            </div>

            {/**/}
            <PrimaryButton className="btn btn-primary w-full normal-case text-lg"
                           onClick={register}>
                Register
            </PrimaryButton>
        </section>
    )
}