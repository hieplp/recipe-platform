import React from "react";
import {Layout} from "~/components/layouts/Layout";
import {SubTitle, Title} from "~/components/ui/Title";
import {RecipeCardList} from "~/components/recipe/RecipeCardList";

export default function RecipesByCategory() {

    const recipes = [
        {
            recipeId: "1",
            title: "Shoes!",
            image: "/photo1.jpeg",
            rating: 3,
            authorName: "John Doe",
            authorAvatar: "/avatar.jpg",
            time: "Yesterday",
            totalComments: 222
        },
        {
            recipeId: "2",
            title: "Shoes!",
            image: "/photo1.jpeg",
            rating: 3,
            authorName: "John Doe",
            authorAvatar: "/avatar.jpg",
            time: "Yesterday",
            totalComments: 221
        },
        {
            recipeId: "3",
            title: "Shoes!",
            image: "/photo1.jpeg",
            rating: 3,
            authorName: "John Doe",
            authorAvatar: "/avatar.jpg",
            time: "Yesterday",
            totalComments: 222
        },
        {
            recipeId: "4",
            title: "Shoes!",
            image: "/photo1.jpeg",
            rating: 3,
            authorName: "John Doe",
            authorAvatar: "/avatar.jpg",
            time: "Yesterday",
            totalComments: 222
        },
        {
            recipeId: "5",
            title: "Shoes!",
            image: "/photo1.jpeg",
            rating: 3,
            authorName: "John Doe",
            authorAvatar: "/avatar.jpg",
            time: "Yesterday",
            totalComments: 222
        },
    ]

    return (
        <Layout>
            <div className="relative w-full mt-3">
                <Title className="my-3">
                    Recipes
                    <span className="text-sm font-normal ml-2">
                       ( 98 recipes )
                    </span>
                </Title>

                <SubTitle className="my-3">
                    One thing I love about this recipe is that it’s so versatile. You can serve it as an appetizer or a
                    main dish, and you can swap out the veggies for whatever you have on hand.
                </SubTitle>

                <div className="flex justify-end w-full">
                    <select className="select select-bordered w-full md:max-w-xs">
                        <option selected>
                            Created Date (Desc)
                        </option>
                        <option>
                            Created Date (Asc)
                        </option>
                        <option>
                            Rating (Desc)
                        </option>
                        <option>
                            Rating (Asc)
                        </option>
                    </select>
                </div>

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