import React from "react";
import {clsx} from "clsx";
import {CategoryCard} from "~/components/category/CategoryCard";
import type Category from "~/types/Category";
// --------------------------------------------------------------------------
// XXX CategorySliderItem
// --------------------------------------------------------------------------


const CategorySliderItem = React.forwardRef<
    HTMLAnchorElement,
    React.HTMLAttributes<HTMLAnchorElement> & Category
>(({
       className,
       ...props
   }, ref) => (
    <CategoryCard ref={ref}
                  className={clsx(
                      "flex flex-none mr-3 items-center justify-center text-center group",
                      className,
                  )}
                  {...props}
    />
));
CategorySliderItem.displayName = "CategorySliderItem";

// --------------------------------------------------------------------------
// XXX CategorySlider
// --------------------------------------------------------------------------

const CategorySlider = React.forwardRef<
    HTMLDivElement,
    React.HTMLAttributes<HTMLDivElement> & { categories: Category[] }
>(({
       className,
       ...props
   }, ref) => (
    <div ref={ref}
         className={clsx(
             className,
             "flex overflow-x-scroll"
         )}
    >
        {
            props.categories.map(category => (
                <CategorySliderItem key={category.categoryId}
                                    {...category}
                />
            ))
        }
    </div>
));
CategorySlider.displayName = "CategorySlider";

// --------------------------------------------------------------------------
// XXX Export
// --------------------------------------------------------------------------
export {CategorySliderItem, CategorySlider};