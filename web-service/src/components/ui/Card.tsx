import React from "react";
import {clsx} from "clsx";
import NextImage from "~/components/ui/NextImage";
import Link from "next/link";
import {type LinkProps} from "next/dist/client/link";

// --------------------------------------------------------------------------
// XXX Card
// --------------------------------------------------------------------------
const Card = React.forwardRef<
    HTMLAnchorElement,
    React.HTMLAttributes<HTMLAnchorElement> & LinkProps
>(({
       className,
       ...props
   }, ref) => (
    <Link ref={ref}
          className={clsx(
              "card w-full",
              className
          )}
          {...props}
    />
))
Card.displayName = "Card"

// --------------------------------------------------------------------------
// XXX Card - Image
// --------------------------------------------------------------------------
const CardImage = React.forwardRef<
    HTMLDivElement,
    React.HTMLAttributes<HTMLDivElement> & { src: string }
>(({
       className,
       src
   }, ref) => (
    <NextImage ref={ref}
               useSkeleton={true}
               src={src}
               height={550}
               width={760}
               className={className}
               imgClassName="h-full w-full"
               alt=""/>
));
CardImage.displayName = "CardImage";

// --------------------------------------------------------------------------
// XXX Card - Header
// --------------------------------------------------------------------------
const CardHeader = React.forwardRef<
    HTMLDivElement,
    React.HTMLAttributes<HTMLDivElement>
>(({className, ...props}, ref) => (
    <div
        ref={ref}
        className={clsx("flex flex-col space-y-1.5 p-6", className)}
        {...props}
    />
))
CardHeader.displayName = "CardHeader"

// --------------------------------------------------------------------------
// XXX Card - Title
// --------------------------------------------------------------------------
const CardTitle = React.forwardRef<
    HTMLParagraphElement,
    React.HTMLAttributes<HTMLHeadingElement> & { as?: "h1" | "h2" | "h3" }
>(({className, as: Comp = "h3", ...props}, ref) => (
    <Comp
        ref={ref}
        className={clsx(
            "card-title",
            className
        )}
        {...props}
    />
))
CardTitle.displayName = "CardTitle"

// --------------------------------------------------------------------------
// XXX Card - Content
// --------------------------------------------------------------------------

const CardContent = React.forwardRef<
    HTMLDivElement,
    React.HTMLAttributes<HTMLDivElement>
>(({className, ...props}, ref) => (
    <div ref={ref} className={clsx("card-body", className)} {...props} />
))
CardContent.displayName = "CardContent"

// --------------------------------------------------------------------------
// XXX Card - Footer
// --------------------------------------------------------------------------
const CardFooter = React.forwardRef<
    HTMLDivElement,
    React.HTMLAttributes<HTMLDivElement>
>(({className, ...props}, ref) => (
    <div
        ref={ref}
        className={clsx("flex p-6 pt-0", className)}
        {...props}
    />
))
CardFooter.displayName = "CardFooter"

// --------------------------------------------------------------------------
// XXX Export
// --------------------------------------------------------------------------
export {
    Card,
    CardImage,
    CardTitle,
    CardHeader,
    CardContent,
    CardFooter,
}