import {CategoryCard} from "~/components/category/CategoryCard";
import React from "react";
import type Category from "~/types/Category";

// --------------------------------------------------------------------------
// XXX CategoryList
// --------------------------------------------------------------------------
const CategoryList = React.forwardRef<
    HTMLDivElement,
    React.HTMLAttributes<HTMLDivElement> & { categories: Category[] }
>(({
       className,
       ...props
   }, ref) => (
    <div ref={ref} className={className}>
        {
            props.categories.map(category => (
                <CategoryCard key={category.categoryId}
                              {...category}
                />
            ))
        }
    </div>
));
CategoryList.displayName = "CategoryList";

// --------------------------------------------------------------------------
// XXX Export
// --------------------------------------------------------------------------
export {CategoryList}