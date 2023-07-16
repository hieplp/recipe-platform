import {Layout} from "~/components/layouts/Layout";
import React from "react";
import Image from "next/image";
import {WhiteDiv} from "~/components/ui/StyledDiv";
import {UpdateProfileForm} from "~/components/forms/user/UpdateProfileForm";

export default function Profile() {
    return (
        <Layout>
            <div className="relative w-full mt-3">
                <WhiteDiv>
                    <p className="text-xl font-bold my-6">
                        Profile
                    </p>

                    <div className="grid grid-cols-1 md:grid-cols-12">
                        <div className="avatar col-span-4
                                        md:items-start md:justify-start
                                        flex items-center justify-center">
                            <div className="w-5/6 rounded">
                                <Image width={150}
                                       height={150}
                                       src="/avatar.jpg"
                                       alt=""/>
                            </div>
                        </div>

                        <div className="col-span-8 mt-5 md:mt-0">
                            <UpdateProfileForm/>
                        </div>
                    </div>
                </WhiteDiv>
            </div>
        </Layout>
    )
}