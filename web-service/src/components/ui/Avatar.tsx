import Image from "next/image";
import React from "react";
import {clsx} from "clsx"

// --------------------------------------------------------------------------
// XXX Avatar
// --------------------------------------------------------------------------

type AvatarProps = {
    avatarImage: string,
    onclick?: () => void,
};
const Avatar = React.forwardRef<HTMLButtonElement, AvatarProps>(
    ({
         avatarImage,
         onclick
     }, ref) => {
        return (
            <button ref={ref}
                    onClick={onclick}
                    type="button"
                    className="flex
                               h-8 w-8
                               md:h-10 md:w-10
                               mr-3 mt-1
                               text-sm
                               rounded-full
                               md:mr-0
                               bg-amber-100
                               focus:ring-4
                               focus:ring-gray-300
                               dark:focus:ring-gray-600"
                    id="user-menu-button"
                    aria-expanded="false"
                    data-dropdown-toggle="user-dropdown"
                    data-dropdown-placement="bottom">
                <AvatarImage avatarImage={avatarImage}/>
            </button>
        );
    });
Avatar.displayName = "Avatar";

// --------------------------------------------------------------------------
// XXX AvatarImage
// --------------------------------------------------------------------------
type AvatarImageProps = {
    avatarImage: string,
    alt?: string,
    className?: string,
    width?: number,
    height?: number
};
const AvatarImage = React.forwardRef<HTMLImageElement, AvatarImageProps>(
    ({
         avatarImage,
         alt,
         className,
         width,
         height
     }, ref) => {
        return (
            <Image ref={ref}
                   width={width == null ? 32 : width}
                   height={height == null ? 32 : height}
                   className={clsx("w-8 h-8 md:w-10 md:h-10 rounded-full", className)}
                   src={avatarImage}
                   alt={alt == null ? '' : alt}/>
        )
    });
AvatarImage.displayName = "AvatarImage";

// --------------------------------------------------------------------------
// XXX Export
// --------------------------------------------------------------------------
export {Avatar, AvatarImage}