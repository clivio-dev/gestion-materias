export type DOMAIN = "https://gestion-materias.com.ar";

export type id = `#${string}`;

// Cambiar nombre de PageBlock por algo mas entendible
export interface PageBlock{
    id: id,
    content: string
}

export interface Page{
    url: URL,
    content: string
}

export type icon = string;

export interface SocialNetwork{
    url: URL,
    icon: icon
}