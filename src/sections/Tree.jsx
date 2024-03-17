import { Fragment } from 'react'
import { Stage, Layer, Text, Rect, Line } from 'react-konva'

const Tree = ({ subjects }) => {
  const rectHeight = 40;
  const rectWidth = 140;
  const rectRadius = 20;
  const textFontSize = 14;
  const rootOffSetX = 300;
  const lineGapX = 150;
  const lineGapY = 100;

  const renderTree = (subject, x, y, level) => {
    const fontSize = textFontSize;
    const height = rectHeight;
    const width = rectWidth < fontSize ? fontSize : rectWidth;
    const radius = rectRadius;
    const gapX = lineGapX;
    const gapY = lineGapY;

    return (
      <>
        <Rect x={x} y={y} height={height} width={width} cornerRadius={radius} fill="lightblue" />
        <Text
          x={x + width / subject.name.length}
          y={y + height / 3}
          text={subject.name}
          fontSize={fontSize}
        />
    
        {subject.correlated.map((correlatedSubject, index) => (
          <Fragment key={index}>
            <Line
              points={[x + width / 2, y + height + 1, x + width / 2 - gapX * (index - 1), y + gapY]}
              stroke="white"
            />
            {renderTree(
              correlatedSubject,
              x - gapX * (index - 1),
              y + gapY,
              level + 1
            )}
          </Fragment>
        ))}
      </>
    );
  };

  return (
    <Stage width={800} height={600}>
      <Layer>
        {subjects.map((subject, index) => (
          <Fragment key={index}>
            {renderTree(subject, index * rootOffSetX, 100, 1)}
          </Fragment>
        ))}
      </Layer>
    </Stage>
  );
};

export default Tree