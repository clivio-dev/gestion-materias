import Header from '../sections/Header'
import Tree from '../sections/Tree'
import Footer from '../sections/Footer'
import Subjects_Data from '../assets/subjects-mockups.json'

const Subjects = () => {
  return (
    <>
      <Header />
      <Tree subjects={Subjects_Data} />
      <Footer />
    </>
  )
}

export default Subjects