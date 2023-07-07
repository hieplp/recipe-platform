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
                                    className="grid-cols-1 md:grid-cols-3
                                               gap-5 md:gap-3"/>
                </div>

                <div className="hero rounded mt-5 h-[48rem]"
                     style={{backgroundImage: 'url(https://daisyui.com/images/stock/photo-1507358522600-9f71e620c44e.jpg)'}}>
                    <div className="hero-overlay bg-opacity-60"></div>
                    <div className="hero-content text-center text-neutral-content">
                        <div className="max-w-md">
                            <p className="mb-5 text-3xl md:text-5xl font-bold">
                                Delicious Recipes
                            </p>
                            <p className="mb-5">
                                Enjoy the best recipes shared by people all over the world.
                            </p>

                            <input type="text"
                                   placeholder="Email Address"
                                   className="input input-bordered w-full mb-3"/>
                            <button className="btn btn-primary w-full text-lg">Join</button>
                        </div>
                    </div>
                </div>

            </Layout>
        </>
    );
}
