import {Layout} from "~/components/layouts/Layout";
import React from "react";
import {Carousel} from "~/components/ui/Carousel";
import {CategorySlider} from "~/components/category/CategorySlider";
import {RecipeCardList} from "~/components/recipe/RecipeCard";

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

    const recipes = [
        {
            recipeId: "1",
            title: "Shoes!",
            image: "/photo1.jpeg",
            rating: 3,
            name: "John Doe",
            avatar: "/avatar.jpg",
            time: "Yesterday",
            totalComments: 222
        },
        {
            recipeId: "1",
            title: "Shoes!",
            image: "/photo1.jpeg",
            rating: 3,
            name: "John Doe",
            avatar: "/avatar.jpg",
            time: "Yesterday",
            totalComments: 222
        },
        {
            recipeId: "1",
            title: "Shoes!",
            image: "/photo1.jpeg",
            rating: 3,
            name: "John Doe",
            avatar: "/avatar.jpg",
            time: "Yesterday",
            totalComments: 222
        },
        {
            recipeId: "1",
            title: "Shoes!",
            image: "/photo1.jpeg",
            rating: 5,
            name: "John Doe",
            avatar: "/avatar.jpg",
            time: "Yesterday",
            totalComments: 222
        },
        {
            recipeId: "1",
            title: "Shoes!",
            image: "/photo1.jpeg",
            rating: 1,
            name: "John Doe",
            avatar: "/avatar.jpg",
            time: "Yesterday",
            totalComments: 1222
        },
    ]

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
                    <RecipeCardList recipes={recipes}
                                    className="grid-cols-1 md:grid-cols-3 gap-5 md:gap-3"/>
                </div>
            </Layout>
        </>
    );
}
