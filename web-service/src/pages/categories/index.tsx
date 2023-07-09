import React from "react";
import {Layout} from "~/components/layouts/Layout";
import {CategoryList} from "~/components/category/CategoryList";
import {Title} from "~/components/ui/Title";

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
                <Title text="Categories" className="my-3"/>
                <CategoryList className="grid mt-5
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