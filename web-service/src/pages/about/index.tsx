import {Layout} from "~/components/layouts/Layout";
import {Title} from "~/components/ui/Title";
import React from "react";
import NextImage from "~/components/ui/NextImage";

export default function About() {
    return (
        <Layout>
            <div className="relative w-full mt-3 space-y-2">
                <Title className="my-3">
                    About
                </Title>

                <p className="font-bold text-2xl">
                    We are a team of passionate foodies who love to cook and bake delicious meals.
                </p>

                <div className="flex justify-center">
                    <NextImage alt=""
                               src="/newsletter.jpg"
                               imgClassName="rounded-xl w-full"
                               width={1500}
                               height={300}
                    />
                </div>

                <p className="my-5">
                    Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean venenatis a sem hendrerit
                    consectetur. Sed vitae purus tortor. Duis facilisis, eros nec dignissim eleifend, felis tortor
                    tempus eros, sagittis molestie leo mi non leo. Interdum et malesuada fames ac ante ipsum primis in
                    faucibus. Etiam vitae lacus est. Proin elementum dolor sed augue gravida, dapibus finibus diam
                    mattis. Suspendisse pretium sit amet leo venenatis scelerisque. Morbi id mi viverra, venenatis magna
                    sed, finibus purus. Nullam maximus mauris ac arcu ultrices, eu viverra tellus euismod. Etiam maximus
                    sagittis diam, molestie molestie lacus volutpat elementum. Sed non est metus. Duis mattis urna vitae
                    ante mattis finibus. Vivamus non arcu ut mi pellentesque hendrerit vitae vitae leo. Quisque id
                    dapibus ipsum. Donec gravida porttitor convallis. Donec dictum enim vel diam hendrerit mollis.
                </p>

                <div className="w-full
                                grid
                                grid-cols-1 md:grid-cols-2
                                gap-2">

                    <NextImage alt=""
                               width={1200}
                               className="w-full h-full"
                               imgClassName="rounded-xl w-full"
                               src="/about_1.jpeg"/>

                    <div className="">
                        <p className="">
                            Donec at dignissim lacus. Nam eget tellus tellus. Vivamus sed urna nec nunc hendrerit tempor
                            vel et dui. Sed porta libero elit, vitae varius felis tempus malesuada. Mauris quis eleifend
                            lacus. Maecenas lacinia tortor ipsum, quis consectetur sapien vehicula et. Sed aliquet, enim
                            et congue fringilla, metus turpis ullamcorper nunc, sit amet luctus nibh lacus quis urna.
                            Pellentesque dapibus est at ultricies fringilla. Class aptent taciti sociosqu ad litora
                            torquent per conubia nostra, per inceptos himenaeos. Suspendisse elementum tortor nibh, id
                            fringilla nisi scelerisque sed. Etiam dictum dapibus leo, vel vulputate nibh egestas in.
                        </p>
                        <br/>
                        <p className="">
                            Donec at dignissim lacus. Nam eget tellus tellus. Vivamus sed urna nec nunc hendrerit tempor
                            vel et dui. Sed porta libero elit, vitae varius felis tempus malesuada. Mauris quis eleifend
                            lacus. Maecenas lacinia tortor ipsum, quis consectetur sapien vehicula et.
                        </p>
                    </div>

                </div>

            </div>
        </Layout>
    )
}