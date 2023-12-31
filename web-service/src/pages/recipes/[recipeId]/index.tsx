import {Layout} from "~/components/layouts/Layout";
import React, {useRef} from "react";
import {LineBreak} from "~/components/ui/Line";
import RecipeTitle from "~/components/forms/recipe/detail/RecipeTitle";
import RecipeSubTitle from "~/components/forms/recipe/detail/RecipeSubTitle";
import NextImage from "~/components/ui/NextImage";
import RecipeBasicInformation from "~/components/forms/recipe/detail/RecipeBasicInformation";
import RecipeIngredients from "~/components/forms/recipe/detail/RecipeIngredients";
import {RecipeNutritionFacts} from "~/components/forms/recipe/detail/RecipeNutritionFacts";
import {RecipeInstructions} from "~/components/forms/recipe/detail/RecipeInstructions";
import RecipeRating from "~/components/forms/recipe/detail/RecipeRating";
import {RecipeReviews} from "~/components/forms/recipe/detail/RecipeReviews";
import {RecipeRecommendation} from "~/components/forms/recipe/detail/RecipeRecommendation";

export default function Recipe() {

    const ingredients = [
        {
            label: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean venenatis a sem hendrerit consectetur. Sed vitae purus tortor. Duis facilisis, eros nec dignissim eleifend, felis tortor tempus eros, sagittis molestie leo mi non leo. Interdum et malesuada fames ac ante ipsum primis in faucibus. Etiam vitae lacus est. Proin elementum dolor sed augue gravida, dapibus finibus diam.",
            checked: true

        },
        {
            label: "For the dough",
            checked: false
        },
        {
            label: "For the dough",
            checked: false
        },
        {
            label: "For the dough",
            checked: false
        }
    ]

    const nutritionFacts = [
        {
            label: "Calories",
            value: "200",
            unit: "kcal"
        },
        {
            label: "Calories",
            value: "200",
            unit: "kcal"
        },
        {
            label: "Calories",
            value: "200",
            unit: "kcal"
        },
        {
            label: "Calories",
            value: "200",
            unit: "kcal"
        }
    ];

    const instructions = [
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean venenatis a sem hendrerit consectetur. Sed vitae purus tortor. Duis facilisis, eros nec dignissim eleifend, felis tortor tempus eros, sagittis molestie leo mi non leo. Interdum et malesuada fames ac ante ipsum primis in faucibus. Etiam vitae lacus est. Proin elementum dolor sed augue gravida, dapibus finibus diam.",
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean venenatis a sem hendrerit consectetur. Sed vitae purus tortor. Duis facilisis, eros nec dignissim eleifend, felis tortor tempus eros, sagittis molestie leo mi non leo. Interdum et malesuada fames ac ante ipsum primis in faucibus. Etiam vitae lacus est. Proin elementum dolor sed augue gravida, dapibus finibus diam.",
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean venenatis a sem hendrerit consectetur. Sed vitae purus tortor. Duis facilisis, eros nec dignissim eleifend, felis tortor tempus eros, sagittis molestie leo mi non leo. Interdum et malesuada fames ac ante ipsum primis in faucibus. Etiam vitae lacus est. Proin elementum dolor sed augue gravida, dapibus finibus diam.",
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean venenatis a sem hendrerit consectetur. Sed vitae purus tortor. Duis facilisis, eros nec dignissim eleifend, felis tortor tempus eros, sagittis molestie leo mi non leo. Interdum et malesuada fames ac ante ipsum primis in faucibus. Etiam vitae lacus est. Proin elementum dolor sed augue gravida, dapibus finibus diam.",
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean venenatis a sem hendrerit consectetur. Sed vitae purus tortor. Duis facilisis, eros nec dignissim eleifend, felis tortor tempus eros, sagittis molestie leo mi non leo. Interdum et malesuada fames ac ante ipsum primis in faucibus. Etiam vitae lacus est. Proin elementum dolor sed augue gravida, dapibus finibus diam.",
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean venenatis a sem hendrerit consectetur. Sed vitae purus tortor. Duis facilisis, eros nec dignissim eleifend, felis tortor tempus eros, sagittis molestie leo mi non leo. Interdum et malesuada fames ac ante ipsum primis in faucibus. Etiam vitae lacus est. Proin elementum dolor sed augue gravida, dapibus finibus diam.",
    ];

    const reviews = [
        {
            avatar: "/avatar.jpg",
            name: "John Doe",
            time: "45 minutes ago",
            rating: 4,
            review: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean venenatis a sem hendrerit consectetur. Sed vitae purus tortor. Duis facilisis, eros nec dignissim eleifend, felis tortor tempus eros, sagittis molestie leo mi non leo. Interdum et malesuada fames ac ante ipsum primis in faucibus. Etiam vitae lacus est. Proin elementum dolor sed augue gravida, dapibus finibus diam.",
            likes: 12,
            isLiked: false
        },
        {
            avatar: "/avatar.jpg",
            name: "John Doe",
            time: "45 minutes ago",
            rating: 3,
            review: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean venenatis a sem hendrerit consectetur. Sed vitae purus tortor. Duis facilisis, eros nec dignissim eleifend, felis tortor tempus eros, sagittis molestie leo mi non leo. Interdum et malesuada fames ac ante ipsum primis in faucibus. Etiam vitae lacus est. Proin elementum dolor sed augue gravida, dapibus finibus diam.",
            likes: 22,
            isLiked: true
        },
        {
            avatar: "/avatar.jpg",
            name: "John Doe",
            time: "45 minutes ago",
            rating: 1,
            review: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean venenatis a sem hendrerit consectetur. Sed vitae purus tortor. Duis facilisis, eros nec dignissim eleifend, felis tortor tempus eros, sagittis molestie leo mi non leo. Interdum et malesuada fames ac ante ipsum primis in faucibus. Etiam vitae lacus est. Proin elementum dolor sed augue gravida, dapibus finibus diam.",
            likes: 12,
            isLiked: false
        }
    ]

    const recommendedRecipes = [
        {
            recipeId: "1",
            image: "/photo1.jpeg",
            title: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
        },
        {
            recipeId: "2",
            image: "/photo1.jpeg",
            title: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
        },
        {
            recipeId: "3",
            image: "/photo1.jpeg",
            title: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
        }
    ]

    const ratingRef = useRef(null);

    return (
        <Layout>
            <div className="relative w-full mt-3 space-y-3">
                <RecipeTitle percent={98}
                             isOwner={true}
                />
                <LineBreak/>

                <RecipeSubTitle avatar="/avatar.jpg"
                                author="John Doe"
                                date="May 2021"
                                totalComments={88}/>
                <LineBreak/>

                <p className="dark:text-white">
                    Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean venenatis a sem hendrerit
                    consectetur. Sed vitae purus tortor. Duis facilisis, eros nec dignissim eleifend, felis tortor
                    tempus eros, sagittis molestie leo mi non leo. Interdum et malesuada fames ac ante ipsum primis
                    in
                    faucibus. Etiam vitae lacus est. Proin elementum dolor sed augue gravida, dapibus finibus diam.
                </p>
                <LineBreak/>

                <div className="flex justify-center">
                    <NextImage alt=""
                               src="/newsletter.jpg"
                               imgClassName="rounded-xl w-full"
                               width={1500}
                               height={300}
                    />
                </div>

                <LineBreak/>
                <RecipeBasicInformation className=""/>
                <LineBreak/>

                <div className="grid
                                grid-cols-1 md:grid-cols-12
                                gap-0 md:gap-5
                                space-y-5 md:space-y-0
                                ">
                    {/*Ingredients*/}
                    <div className="col-span-4
                                    space-y-5">
                        <p className="text-2xl font-bold dark:text-white">
                            Ingredients
                        </p>

                        <RecipeIngredients title="For the dough"
                                           ingredients={ingredients}
                                           className="space-y-2"/>

                        <RecipeNutritionFacts facts={nutritionFacts}/>
                    </div>

                    {/*Instructions*/}
                    <div className="col-span-8 space-y-3">
                        <p className="text-2xl font-bold dark:text-white">
                            Instructions
                        </p>

                        <RecipeInstructions className="space-y-2 dark:text-white"
                                            instructions={instructions}/>
                    </div>
                </div>
                <LineBreak/>

                {/*Rating*/}
                <div className="space-y-2
                                pb-2
                                border-b-8 border-b-blue-700">
                    <p className="text-2xl font-bold text-white">
                        Already made this?
                    </p>

                    <button className="btn btn-outline btn-primary"
                            onClick={() => {
                                // eslint-disable-next-line @typescript-eslint/ban-ts-comment
                                // @ts-ignore
                                // eslint-disable-next-line @typescript-eslint/no-unsafe-call
                                ratingRef.current.scrollIntoView({
                                    behavior: "smooth",
                                    block: "start"
                                });
                            }}>
                        Share your feedback
                    </button>
                </div>

                {/*Rating List*/}
                <div className="space-y-2">
                    <p className="text-2xl font-bold dark:text-white">
                        Reviews
                    </p>
                    <LineBreak/>

                    <RecipeReviews reviews={reviews}/>

                    <div className="w-full flex justify-center mt-10">
                        <button className="btn w-32 btn-outline btn-primary">Load More</button>
                    </div>

                </div>

                {/*Rating*/}
                <LineBreak/>
                <RecipeRating ref={ratingRef} isLogin={false}/>

                <LineBreak/>
                <div className="space-y-2">
                    <p className="text-2xl font-bold dark:text-white">
                        You might also like
                    </p>

                    <RecipeRecommendation recipes={recommendedRecipes}/>
                </div>

            </div>
        </Layout>
    )
}