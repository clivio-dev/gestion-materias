import '../styles/components/Subject.css'
import { useState } from "react"

const Subject = ({ id, name, initialIsApproved = false, correlated = [], onUpdateApproval, children }) => {
  const [isApproved, setIsApproved] = useState(initialIsApproved)

  const className = isApproved ? "subject--approved" : "subject--unapproved"

  const updateCorrelatedSubjects = (updatedIsApproved) => {
    correlated.forEach((correlatedSubject) => {
      onUpdateApproval(correlatedSubject.id, updatedIsApproved)
    })
  }

  const handleClick = () => {
    const updatedIsApproved = !isApproved
    
    setIsApproved(updatedIsApproved)
    onUpdateApproval(id, updatedIsApproved)
    updateCorrelatedSubjects(updatedIsApproved);
  }

  return (
    <>
      <div onClick={handleClick} className={`subject ${className}`}>
        <h3 className='subject__name'>{name}</h3>
        <h4>{id}</h4>
      </div>
      {children}
    </>
  )
}

export default Subject 