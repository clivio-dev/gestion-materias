import { SocialNetwork, id } from "./types";

interface Props{
    id: id
    SocialNetworks: SocialNetwork[]
    content: string
}

const Footer: React.FC<Props> = ({ id, content, SocialNetworks }) => {
    return(
        <footer id={id} className="flex align-center justify-between w-screen bg-slate-900 text-white">
            <div className="flex-1">
                {content}
            </div>
            <ul className="w-40 flex align-center justify-around">
                {SocialNetworks.map((network, i) => {
                    return(
                        <li 
                            className=""
                            key={i}
                        >
                            <a 
                                href={network.url.href} 
                                target="_blank" 
                                rel="noopener noreferrer"
                            >
                                <i className={network.icon}></i>
                            </a>
                        </li>
                    );
                })}
            </ul>
        </footer>
    );
}

export default Footer;