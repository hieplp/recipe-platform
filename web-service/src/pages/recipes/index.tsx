import React from "react";
import {Layout} from "~/components/layouts/Layout";
import {Title} from "~/components/ui/Title";
import {RecipeCardList} from "~/components/recipe/RecipeCardList";

export default function Recipes() {

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
        <Layout>
            <div className="relative w-full mt-3">
                <Title text="Recipes" className="my-3"/>
                <RecipeCardList className="grid mt-5
                                           grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 2xl:grid-cols-4
                                           gap-5 md:gap-5"
                                recipes={recipes}/>

                <div className="w-full flex justify-center mt-10">
                    <button className="btn w-32 btn-outline btn-primary">Load More</button>
                </div>
            </div>
        </Layout>
    );
}