import {Layout} from "~/components/layouts/Layout";
import React from "react";
import {Carousel} from "~/components/ui/Carousel";
import {CategorySlider} from "~/components/category/CategorySlider";

export default function Home() {

    const carouselItems = [
        {
            image: "https://flowbite.com/docs/images/carousel/carousel-1.svg"
        },
        {

            image: "https://flowbite.com/docs/images/carousel/carousel-2.svg"
        }
    ];

    const categories = [
        {
            name: "Pasta",
            image: "https://flowbite.com/docs/images/carousel/carousel-1.svg"
        },
        {
            name: "Pasta",
            image: "https://flowbite.com/docs/images/carousel/carousel-1.svg"
        }
    ];

    return (
        <>
            <Layout>

                <div className="relative w-full">
                    <Carousel items={carouselItems} className="h-56 md:h-96 lg:h-[48rem]"/>
                </div>

                <div className="relative w-full mt-3">
                    <p className="text-xl font-bold my-6">
                        Popular Categories
                    </p>
                    <CategorySlider items={categories}/>
                </div>

                <div className="relative w-full mt-3">
                    <p className="text-xl font-bold my-6">
                        Super Delicious
                    </p>

                    <div className="grid grid-cols-3">

                        <div className="bg-amber-200">
                            1222

                        </div>

                    </div>
                </div>

            </Layout>
        </>
    );
}
