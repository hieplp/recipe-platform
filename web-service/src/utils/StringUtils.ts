import {v4} from "uuid";

export function uuid(): string {
    return v4().replace(/-/g, '');
}