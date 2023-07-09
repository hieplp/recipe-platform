import React from "react";
import {Layout} from "~/components/layouts/Layout";
import {CategoryList} from "~/components/category/CategoryList";
import {LineBreak} from "~/components/ui/Line";

export default function Categories() {

    const categories = [
        {
            name: "Pasta",
            image: "https://flowbite.com/docs/images/carousel/carousel-1.svg"
        },
        {
            name: "Pasta",
            image: "https://flowbite.com/docs/images/carousel/carousel-1.svg"
        },
        {
            name: "Pasta",
            image: "https://flowbite.com/docs/images/carousel/carousel-1.svg"
        },
        {
            name: "Pasta",
            image: "https://flowbite.com/docs/images/carousel/carousel-1.svg"
        },
        {
            name: "Pasta",
            image: "https://flowbite.com/docs/images/carousel/carousel-1.svg"
        },
        {
            name: "Pasta",
            image: "https://flowbite.com/docs/images/carousel/carousel-1.svg"
        },
        {
            name: "Pasta",
            image: "https://flowbite.com/docs/images/carousel/carousel-1.svg"
        },
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
        <Layout>
            <div className="relative w-full mt-3">
                <p className="text-xl font-bold my-6">
                    Categories
                </p>
                <LineBreak/>
                <CategoryList className="grid mt-10
                                         grid-cols-2 md:grid-cols-5 xl:grid-cols-6 2xl:grid-cols-9
                                         gap-1 md:gap-5"
                              categories={categories}/>

                <div className="w-full flex justify-center mt-10">
                    <button className="btn w-32 btn-outline btn-primary">Load More</button>
                </div>
            </div>
        </Layout>
    );
}